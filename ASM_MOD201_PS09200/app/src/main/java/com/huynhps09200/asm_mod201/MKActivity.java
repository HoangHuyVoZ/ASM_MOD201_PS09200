package com.huynhps09200.asm_mod201;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.huynhps09200.asm_mod201.Database.LoginDao;
import com.huynhps09200.asm_mod201.Model.Login;

public class MKActivity extends Fragment {
    EditText edtuser,edtpass,edtpassre1,edtpassre2;
    Button btnHuy,btnMK;
    LoginDao loginDao;
    Login login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_mk,container,false);
        loginDao=new LoginDao(getContext());
        edtuser=view.findViewById(R.id.edtuser);
        edtpass=view.findViewById(R.id.edtpass);
        edtpassre1=view.findViewById(R.id.edtpassre1);
        edtpassre2=view.findViewById(R.id.edtpassre2);
        btnHuy=view.findViewById(R.id.btnHuy);
        btnMK=view.findViewById(R.id.btnMK);
        RelativeLayout mk=view.findViewById(R.id.mk);
        Animation fadeIn= AnimationUtils.loadAnimation(getContext(),R.anim.fadein);
        mk.startAnimation(fadeIn);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Đổi mật khẩu không thành công", Toast.LENGTH_LONG).show();
                Intent i =new Intent(getContext(), HomeActivity.class);
                startActivity(i);
            }
        });
        btnMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtuser.getText().toString();
                String pass = edtpassre1.getText().toString();
                if (edtuser.getText().length() != 0 && edtpass.getText().length() != 0 && edtpassre1.getText().length() != 0 && edtpassre2.getText().length() != 0) {
                    if (loginDao.searchTK(user) == true) {
                        if (loginDao.searchMK(user).equals(edtpass.getText().toString())) {
                            if(edtpassre1.getText().toString().equals(edtpassre2.getText().toString())) {
                                login=new Login(user,pass);
                                loginDao.update(login);
                                Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
                                Intent i =new Intent(getContext(), HomeActivity.class);
                                startActivity(i);
                            }else {
                                Toast.makeText(getContext(), "Nhập mật khẩu mới không trùng", Toast.LENGTH_LONG).show();
                            }}else {
                            Toast.makeText(getContext(), "Nhập mật khẩu không đúng", Toast.LENGTH_LONG).show();
                        }}else {
                        Toast.makeText(getContext(), "Tài khoản không tồn tại", Toast.LENGTH_LONG).show();
                    }}else {
                    Toast.makeText(getContext(), "Chưa điền đủ thông tin", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
