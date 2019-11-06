package com.huynhps09200.asm_mod201.Fragment;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.huynhps09200.asm_mod201.Adapter.MapAdapter;
import com.huynhps09200.asm_mod201.R;

public class MapFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    MapAdapter adapter;
    FragmentManager fragmentManager;
    private FragmentActivity myContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.map_fragment,container,false);
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);
        addControl();
        return view;
    }
    public void addControl(){
        fragmentManager=myContext.getSupportFragmentManager();
        adapter=new MapAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position==0){
                    viewPager.getAdapter().notifyDataSetChanged();

                }else {

                }
                Log.d("pos",position+"");
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    public void onAttach(Activity activity) {
        myContext= (FragmentActivity) activity;
        super.onAttach(activity);
    }


}