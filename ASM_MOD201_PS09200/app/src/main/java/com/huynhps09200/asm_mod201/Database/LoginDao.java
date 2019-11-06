package com.huynhps09200.asm_mod201.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huynhps09200.asm_mod201.Model.Login;

public class LoginDao {
    private SQLiteDatabase sqLiteDatabaseW;
    private SQLiteDatabase sqLiteDatabaseR;
    public LoginDao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDatabaseW=dbHelper.getWritableDatabase();
        sqLiteDatabaseR=dbHelper.getReadableDatabase();
    }

    public long update(Login s) {
        ContentValues values = new ContentValues();
        values.put("pass", s.pass);
       return sqLiteDatabaseW.update("Login", values, "user=?", new String[]{(s.user)});
    }

    public boolean searchTK(String user){
        String query="select user from Login";
        Cursor cursor=sqLiteDatabaseR.rawQuery(query,null);
        String User;
        if(cursor.moveToFirst()){
            do{
                User=cursor.getString(cursor.getColumnIndex("user"));
                if(User.equals(user)){
                    return true;
                }
            }while (cursor.moveToNext());
        }
        return false;
    }

    public String searchMK(String user){
        String query="select user,pass from Login";
        Cursor cursor=sqLiteDatabaseR.rawQuery(query,null);

        String User;
        String pass="not found";
        if(cursor.moveToFirst()){
            do{
                User=cursor.getString(cursor.getColumnIndex("user"));
                if(User.equals(user)){
                    pass=cursor.getString(cursor.getColumnIndex("pass"));
                    break;
                }
            }while (cursor.moveToNext());
        }
        return pass;
    }
}
