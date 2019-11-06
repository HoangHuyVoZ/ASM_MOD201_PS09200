package com.huynhps09200.asm_mod201;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.huynhps09200.asm_mod201.Fragment.AddCourse;
import com.huynhps09200.asm_mod201.Fragment.Course_Fragment;
import com.huynhps09200.asm_mod201.Fragment.MapFragment;
import com.huynhps09200.asm_mod201.Fragment.NewsFragment;
import com.huynhps09200.asm_mod201.Fragment.SocialFragment;

public class HomeActivity extends AppCompatActivity {
    ActionBar toolbar;
    FragmentManager fragmentManager;
    Animation FadeIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=getSupportActionBar();
        FadeIn= AnimationUtils.loadAnimation(this,R.anim.fadein);
        BottomNavigationView navigation=findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.startAnimation(FadeIn);
        fragmentManager =   getSupportFragmentManager();
        FragmentTransaction fragment = fragmentManager.beginTransaction();//
        Course_Fragment course = new Course_Fragment();
        fragment.replace(R.id.container, course);
        fragment.commit();
        toolbar.setTitle("Course");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.changePass) {
            FragmentTransaction fragment = fragmentManager.beginTransaction();//
            MKActivity course = new MKActivity();
            fragment.replace(R.id.container, course);
            fragment.commit();
            toolbar.setTitle("Đổi mật khẩu");
        }if(item.getItemId()==R.id.Logout){
            Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(i);
        }if(item.getItemId()==R.id.exit){
            Toast.makeText(getBaseContext(), "Thoát thành công ", Toast.LENGTH_LONG).show();
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            startActivity(i);
            finish();
        }

        return false;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.Course:
                    toolbar.setTitle("Course");
                    fragment=new Course_Fragment();
                    loadFragment(fragment);
                    return true;
                case R.id.maps:
                    toolbar.setTitle("Maps");
                    fragment=new MapFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.news:
                    toolbar.setTitle("News");
                    fragment=new NewsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.Social:
                    toolbar.setTitle("Social");
                    fragment=new SocialFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
