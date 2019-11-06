package com.huynhps09200.asm_mod201.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static final String dbName ="Khoa Hoc";
    static final int versionDB =1;

    public DBHelper( Context context) {
        super(context, dbName, null , versionDB);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1="CREATE TABLE Login (" +
                "user TEXT PRIMARY KEY ," +
                "pass TEXT NOT NULL)";
        db.execSQL(sql1);
        sql1="insert into Login(user,pass) values ('admin','admin')";
        db.execSQL(sql1);

        String sql="CREATE TABLE KhoaHoc ("+
                "maKH text primary key ,"+
                "mon text not null,"+
                "GV text not null,"+
                "ngayBD text not null,"+
                "ngayKT text not null,"+
                "mota text not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists KhoaHoc";
        db.execSQL(sql);
        String sqlLogin = "drop table if exists Login";
        db.execSQL(sqlLogin);
    }
}
