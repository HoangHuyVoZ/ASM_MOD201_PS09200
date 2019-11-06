package com.huynhps09200.asm_mod201.Adapter;



import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.huynhps09200.asm_mod201.CS1_Fragment;
import com.huynhps09200.asm_mod201.CS2_Fragment;
import com.huynhps09200.asm_mod201.CS3_Fragment;

public class MapAdapter extends FragmentStatePagerAdapter {

    public MapAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag= new CS1_Fragment();
                break;
            case 1:
                frag=new CS2_Fragment();
                break;
            case 2:
                frag=new CS3_Fragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;}

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "CS1";

                break;
            case 1:
                title = "CS2";
                break;
            case 2:
                title ="CS3" ;
                break;
        }
        return title;
    }
}
