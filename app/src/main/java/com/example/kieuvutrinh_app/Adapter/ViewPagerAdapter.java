package com.example.kieuvutrinh_app.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.kieuvutrinh_app.Fragment.Buoc1Fragment;
import com.example.kieuvutrinh_app.Fragment.Buoc2Fragment;
import com.example.kieuvutrinh_app.Fragment.Buoc3Fragment;
import com.example.kieuvutrinh_app.Fragment.Buoc4Fragment;
import com.example.kieuvutrinh_app.Fragment.Buoc5Fragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new Buoc1Fragment();
            case 1:return new Buoc2Fragment();
            case 2:return new Buoc3Fragment();
            case 3:return new Buoc4Fragment();
            case 4:return new Buoc5Fragment();
            default:return new Buoc1Fragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
