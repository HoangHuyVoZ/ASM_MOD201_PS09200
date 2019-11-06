package com.huynhps09200.asm_mod201.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huynhps09200.asm_mod201.Database.KhoaHocDao;
import com.huynhps09200.asm_mod201.Model.KhoaHoc;
import com.huynhps09200.asm_mod201.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CourseAdapter extends  RecyclerView.Adapter<CourseAdapter.ViewHolder>{
    ArrayList<KhoaHoc> list;
    Context context;
    KhoaHocDao khoaHocDao;
    CourseAdapter adapter;
    public CourseAdapter( Context context,ArrayList<KhoaHoc> list) {
        this.list = list;
        this.context = context;
        khoaHocDao=new KhoaHocDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.course_lv,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final KhoaHoc khoaHoc = list.get(i);
        viewHolder.TvMon.setText(khoaHoc.getMon());
        viewHolder.TvMaKH.setText(khoaHoc.getMaKH());
        viewHolder.TvGV.setText(khoaHoc.getGV());

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có chắc muốn xóa !!!");
                builder.setCancelable(false);

                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(context, "Xóa không thành công ", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Xóa thành công ", Toast.LENGTH_LONG).show();
                        delete(khoaHoc);
                        load();
                }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.update_course);
                dialog.setCancelable(false);
                dialog.show();
                final EditText maKH=dialog.findViewById(R.id.TvMaKH);
                final EditText mon=dialog.findViewById(R.id.TvMon);
                final EditText GV=dialog.findViewById(R.id.TvGV);
                final EditText mota=dialog.findViewById(R.id.edtMota);
                final Button NgayBD=dialog.findViewById(R.id.btnNgayBD);
                final Button NgayKT=dialog.findViewById(R.id.btnNgayKT);
                final Button btnHuy=dialog.findViewById(R.id.btnHuy);
                final Button btnCapnhat=dialog.findViewById(R.id.btnDY);

                maKH.setText(khoaHoc.getMaKH());
                mon.setText(khoaHoc.getMon());
                GV.setText(khoaHoc.getGV());
                NgayBD.setText(khoaHoc.getNgayBD());
                NgayKT.setText(khoaHoc.getNgayKT());
                mota.setText(khoaHoc.getMota());
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                NgayBD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar calendar=Calendar.getInstance();
                        //Date
                        int Day=calendar.get(Calendar.DAY_OF_MONTH);
                        int Month=calendar.get(Calendar.MONTH);
                        int Year=calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year,month,dayOfMonth);
                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                                NgayBD.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        },Year,Month,Day);
                        datePickerDialog.show();
                    }
                });
                NgayKT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar calendar=Calendar.getInstance();
                        //Date
                        int Day=calendar.get(Calendar.DAY_OF_MONTH);
                        int Month=calendar.get(Calendar.MONTH);
                        int Year=calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year,month,dayOfMonth);
                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                                NgayKT.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        },Year,Month,Day);
                        datePickerDialog.show();
                    }
                });
                btnCapnhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String MaKH = khoaHoc.getMaKH();
                        String Mon=mon.getText().toString();
                        String gv = GV.getText().toString();
                        String ngayBD = khoaHoc.getNgayBD();
                        String ngayKT = khoaHoc.getNgayKT();
                        String Mota = mota.getText().toString();
                        KhoaHoc khoaHoc1 = new KhoaHoc(MaKH,Mon,gv,ngayBD,ngayKT,Mota);
                        if(mon.getText().length()!=0 && GV.getText().length()!=0 && mota.getText().length()!=0 && maKH.getText().length()!=0){
                            int check = khoaHocDao.update(khoaHoc1);
                            if (check > 0) {
                                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                load();
                                notifyDataSetChanged();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(context, "cập nhật thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, "Xin nhập đủ thông tin", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView edit,delete;
        TextView TvMon,TvMaKH,TvGV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edit=itemView.findViewById(R.id.btnEdit);
            delete=itemView.findViewById(R.id.btndelete);
            TvMon=itemView.findViewById(R.id.TvMon);
            TvGV=itemView.findViewById(R.id.TvGV);
            TvMaKH=itemView.findViewById(R.id.TvMaKH);


        }

    }
    public void delete(KhoaHoc khoaHoc){
        khoaHocDao.delete(khoaHoc);
        list.clear();
        notifyDataSetChanged();

    }

    public void load() {
        list=khoaHocDao.getAll();
        adapter=new CourseAdapter(context,list);
        adapter.notifyDataSetChanged();
    }
}
