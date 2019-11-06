package com.huynhps09200.asm_mod201.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huynhps09200.asm_mod201.Model.Tintuc;
import com.huynhps09200.asm_mod201.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<Tintuc> {

    public NewsAdapter(Context context, int resource, List<Tintuc> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.news_lv, null);
        }
        Tintuc p = getItem(position);
        if (p != null) {
            TextView title =  view.findViewById(R.id.tieude);
            TextView description=view.findViewById(R.id.mota);
            ImageView image=view.findViewById(R.id.image);
            title.setText(p.title);
            description.setText(p.description);
            Picasso.with(getContext()).load(p.image).into(image);


        }
        return view;
    }

}