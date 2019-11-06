package com.huynhps09200.asm_mod201.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.huynhps09200.asm_mod201.Adapter.NewsAdapter;
import com.huynhps09200.asm_mod201.LoadNewsActivity;
import com.huynhps09200.asm_mod201.Model.Tintuc;
import com.huynhps09200.asm_mod201.R;
import com.huynhps09200.asm_mod201.XMLDOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsFragment extends Fragment {
    ListView Lv;
    ArrayList<Tintuc> tintuc;
    NewsAdapter adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_fragment,container,false);
        Lv=view.findViewById(R.id.Lv);
        tintuc=new ArrayList<Tintuc>();
        new ReadRss().execute("https://vnexpress.net/rss/giao-duc.rss");
        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(),LoadNewsActivity.class);
                intent.putExtra("link",tintuc.get(position).link);
                startActivity(intent);
            }
        });
        return view;
    }
    private class ReadRss extends AsyncTask<String,Void,String> {
        StringBuilder content=new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                InputStreamReader inputStreamReader=new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser xmldomParser=new XMLDOMParser();
            Document document=xmldomParser.getDocument(s);
            NodeList nodeList=document.getElementsByTagName("item");
            NodeList nodeListdeps=document.getElementsByTagName("description");
            String hinhanh="";
            String title="";
            String link="";
            String deps="";
            for (int i=0;i<nodeList.getLength();i++){
                String cdata=nodeListdeps.item(i+1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher=p.matcher(cdata);
                if(matcher.find()){
                    hinhanh=matcher.group(1);
                    deps=cdata.replaceAll("<.*?>"," ");
                }
                Element element= (Element) nodeList.item(i);
                title+=xmldomParser.getValue(element,"title");
                link=xmldomParser.getValue(element,"link");
//                deps+=xmldomParser.getValue(element,"description/![CDATA[");
//                deps=removeTags(deps);
                tintuc.add(new Tintuc(title,link,hinhanh,deps));

            }
            adapter=new NewsAdapter(getContext(),android.R.layout.simple_list_item_1,tintuc);
            Lv.setAdapter(adapter);
            super.onPostExecute(s);
        }
    }
    public String removeTags(String str) {
        str = str.replaceAll("<.*?>", " ");
        str = str.replaceAll("\\s+", " ");
        return str;
    }
}


