package com.huynhps09200.asm_mod201.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huynhps09200.asm_mod201.Model.KhoaHoc;

import java.util.ArrayList;

public class KhoaHocDao {
    DBHelper dbHelper;
    public  KhoaHocDao(Context context){
        dbHelper=new DBHelper(context);

    }
    public boolean insert(KhoaHoc khoaHoc) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKH",khoaHoc.getMaKH());
        values.put("mon",khoaHoc.getMon());
        values.put("GV",khoaHoc.getGV());
        values.put("ngayBD",khoaHoc.getNgayBD());
        values.put("ngayKT",khoaHoc.getNgayKT());
        values.put("mota",khoaHoc.getMota());
        long check = db.insert("KhoaHoc", null, values);
        if(check>0){
            return true;
        }else {
            return false;
        }
    }

    public int update(KhoaHoc khoaHoc) { // hàm update
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mon",khoaHoc.getMon());
        values.put("GV",khoaHoc.getGV());
        values.put("ngayBD",khoaHoc.getNgayBD());
        values.put("ngayKT",khoaHoc.getNgayKT());
        values.put("mota",khoaHoc.getMota());
        return  db.update("KhoaHoc",values,"maKH=?",new String[]{khoaHoc.getMaKH()}); // xóa theo mã loại

    }

    public void delete(KhoaHoc khoaHoc){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("KhoaHoc","MaKH=?",new String[]{khoaHoc.getMaKH()});
    }
    public ArrayList<KhoaHoc> getAll(){
        ArrayList<KhoaHoc> list=new ArrayList<>();
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        String sql= "select * from KhoaHoc ";
        Cursor cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            KhoaHoc khoaHoc=new KhoaHoc();
            khoaHoc.setMaKH(cursor.getString(cursor.getColumnIndex("maKH")));
            khoaHoc.setMon(cursor.getString(cursor.getColumnIndex("mon")));
            khoaHoc.setGV(cursor.getString(cursor.getColumnIndex("GV")));
            khoaHoc.setNgayBD(cursor.getString(cursor.getColumnIndex("ngayBD")));
            khoaHoc.setNgayKT(cursor.getString(cursor.getColumnIndex("ngayKT")));
            khoaHoc.setMota(cursor.getString(cursor.getColumnIndex("mota")));
            list.add(khoaHoc);
            cursor.moveToNext();
        }
        return  list;
    }
}
