package com.huynhps09200.asm_mod201.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huynhps09200.asm_mod201.Adapter.CourseAdapter;
import com.huynhps09200.asm_mod201.Database.KhoaHocDao;
import com.huynhps09200.asm_mod201.Model.KhoaHoc;
import com.huynhps09200.asm_mod201.R;

import java.util.ArrayList;
import java.util.List;

public class Course_Fragment extends Fragment {
    TextView TVcourse;
    RecyclerView LV;
    Button btnAdd;
    Animation translatex;
    RecyclerView recyclerViewKH;
    CourseAdapter adapter;
    KhoaHocDao khoaHocDao;
    List<KhoaHoc> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.course_fragment,container,false);
        TVcourse=view.findViewById(R.id.Tvcourse);
        LV=view.findViewById(R.id.LV);
        btnAdd=view.findViewById(R.id.add);
        translatex= AnimationUtils.loadAnimation(getContext(),R.anim.translatex);
        TVcourse.startAnimation(translatex);
        LV.startAnimation(translatex);
        btnAdd.startAnimation(translatex);
        khoaHocDao=new KhoaHocDao(getContext());
        recyclerViewKH=view.findViewById(R.id.LV);
        khoaHocDao=new KhoaHocDao(getContext());
        recyclerViewKH.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewKH.setLayoutManager(linearLayoutManager);
        list=new ArrayList<>();

        list=khoaHocDao.getAll();
        adapter=new CourseAdapter(getContext(),(ArrayList<KhoaHoc>) list);
        recyclerViewKH.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new AddCourse()).commit();

            }
        });
        return view;
    }
}
