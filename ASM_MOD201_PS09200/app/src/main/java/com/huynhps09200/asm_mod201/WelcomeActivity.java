package com.huynhps09200.asm_mod201;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    ImageView vien1,vien2,ongvang;
    Animation fadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        vien1=findViewById(R.id.nen1);
        vien2=findViewById(R.id.nen2);
        fadeIn= AnimationUtils.loadAnimation(this,R.anim.fadein);
        ongvang=findViewById(R.id.ongvang);
        vien1.startAnimation(fadeIn);
        vien2.startAnimation(fadeIn);
        ongvang.startAnimation(fadeIn);

        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(2000);
                } catch (Exception e) {

                }
                finally
                {
                    Intent i=new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        };
        bamgio.start();
    }
    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause(){
        super.onPause();
        finish();
    }
}
