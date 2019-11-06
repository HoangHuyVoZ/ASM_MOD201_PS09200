package com.huynhps09200.asm_mod201;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.huynhps09200.asm_mod201.Database.LoginDao;
import com.huynhps09200.asm_mod201.Model.Login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    ImageView nen1,nen2,fpt;
    EditText edtUser,edtPass;
    Button btnLogin;
    LoginDao loginDao;
    Animation translatey,translatex;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inits();
        loginDao=new LoginDao(this);
        nen1.startAnimation(translatex);
        nen2.startAnimation(translatex);
        fpt.startAnimation(translatex);
        edtUser.startAnimation(translatey);
        edtPass.startAnimation(translatey);
        btnLogin.startAnimation(translatey);
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.huynhps09200.asm_mod201", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                if (edtUser.getText().length() != 0 && edtPass.getText().length() != 0) {
                    if (loginDao.searchTK(user) == true) {
                        if (edtPass.getText().toString().equals(loginDao.searchMK(user))) {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Mật khẩu không đúng", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Tài khoản không tồn tại", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Chưa điền đủ thông tin", Toast.LENGTH_LONG).show();
                }
            }
        });


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "Đăng nhập không thành công", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(getApplicationContext(), "Đăng nhập bị lỗi", Toast.LENGTH_LONG).show();
                    }
                });
            }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void inits(){
        nen1=findViewById(R.id.nen1);
        nen2=findViewById(R.id.nen2);
        fpt=findViewById(R.id.fpt);
        edtUser=findViewById(R.id.user);
        edtPass=findViewById(R.id.pass);
        btnLogin=findViewById(R.id.btnLogin);
        translatey= AnimationUtils.loadAnimation(this,R.anim.translatey);
        translatex= AnimationUtils.loadAnimation(this,R.anim.translatex);
    }
}
