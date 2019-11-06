package com.huynhps09200.asm_mod201.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.huynhps09200.asm_mod201.Adapter.CourseAdapter;
import com.huynhps09200.asm_mod201.Database.KhoaHocDao;
import com.huynhps09200.asm_mod201.Model.KhoaHoc;
import com.huynhps09200.asm_mod201.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddCourse extends Fragment {
    EditText edtMa,edtTen,edtGV,edtMota;
    Button btnNgayBD,btnNgayKT,btnHuy,btnDY;
    KhoaHocDao khoaHocDao;
    KhoaHoc khoaHoc;
    Animation translatey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_course,container,false);
        edtMa=view.findViewById(R.id.TvMaKH);
        edtTen=view.findViewById(R.id.TvMon);
        edtGV=view.findViewById(R.id.TvGV);
        edtMota=view.findViewById(R.id.edtMota);
        btnDY=view.findViewById(R.id.btnDY);
        btnHuy=view.findViewById(R.id.btnHuy);
        btnNgayBD=view.findViewById(R.id.btnNgayBD);
        btnNgayKT=view.findViewById(R.id.btnNgayKT);
        translatey= AnimationUtils.loadAnimation(getContext(),R.anim.translatey);
        edtMota.startAnimation(translatey);
        edtGV.startAnimation(translatey);
        edtTen.startAnimation(translatey);
        edtMa.startAnimation(translatey);
        btnNgayKT.startAnimation(translatey);
        btnNgayBD.startAnimation(translatey);
        btnHuy.startAnimation(translatey);
        btnDY.startAnimation(translatey);

        khoaHocDao=new KhoaHocDao(getContext());


        btnDY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khoaHoc=new KhoaHoc();
                String maKH=edtMa.getText().toString();
                String mon=edtTen.getText().toString();
                String gv=edtGV.getText().toString();
                String mota=edtMota.getText().toString();
                String bd=btnNgayBD.getText().toString();
                String kt=btnNgayKT.getText().toString();
                khoaHoc.setMaKH(maKH);
                khoaHoc.setMon(mon);
                khoaHoc.setGV(gv);
                khoaHoc.setNgayBD(bd);
                khoaHoc.setNgayKT(kt);
                khoaHoc.setMota(mota);
                if(edtTen.getText().length()!=0 && edtGV.getText().length()!=0 && edtMa.getText().length()!=0 && edtMota.getText().length()!=0 ){
                    if(khoaHocDao.insert(khoaHoc)){
                        Toast.makeText(getContext(), "Thêm khóa học thành Công", Toast.LENGTH_SHORT).show();
                        int commit = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new Course_Fragment()).commit();
                    }else {
                        Toast.makeText(getContext(), " Thêm khóa học thất Bại", Toast.LENGTH_SHORT).show();
                    }}else {
                    Toast.makeText(getContext(), "Chưa điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }}
        });
        btnNgayBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseDateStart();
            }
        });
        btnNgayKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseDateEnd();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new Course_Fragment()).commit();
                Toast.makeText(getContext(), "__<->_<->__", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void ChooseDateStart(){
        final Calendar calendar=Calendar.getInstance();
        //Date
        int Day=calendar.get(Calendar.DAY_OF_MONTH);
        int Month=calendar.get(Calendar.MONTH);
        int Year=calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                btnNgayBD.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },Year,Month,Day);
        datePickerDialog.show();
    }
    public void ChooseDateEnd(){
        final Calendar calendar=Calendar.getInstance();
        //Date
        int Day=calendar.get(Calendar.DAY_OF_MONTH);
        int Month=calendar.get(Calendar.MONTH);
        int Year=calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                btnNgayKT.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },Year,Month,Day);
        datePickerDialog.show();
    }
}
