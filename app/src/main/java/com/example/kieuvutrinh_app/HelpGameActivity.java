package com.example.kieuvutrinh_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kieuvutrinh_app.Adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HelpGameActivity extends AppCompatActivity  {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_help_game);
            bottomNavigationView = findViewById(R.id.bottomNavi);
            viewPager = findViewById(R.id.viewPager);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.buoc1:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.buoc2:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.buoc3:
                            viewPager.setCurrentItem(2);
                            break;
                        case R.id.buoc4:
                            viewPager.setCurrentItem(3);
                            break;
                        case R.id.buoc5:
                            viewPager.setCurrentItem(4);
                            break;
                        default:
                            viewPager.setCurrentItem(0);
                    }
                    return true;
                }
            });
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    switch (position) {
                        case 0:
                            bottomNavigationView.getMenu().findItem(R.id.buoc1).setChecked(true);
                            break;
                        case 1:
                            bottomNavigationView.getMenu().findItem(R.id.buoc2).setChecked(true);
                            break;
                        case 2:
                            bottomNavigationView.getMenu().findItem(R.id.buoc3).setChecked(true);
                            break;
                        case 3:
                            bottomNavigationView.getMenu().findItem(R.id.buoc4).setChecked(true);
                            break;
                        case 4:
                            bottomNavigationView.getMenu().findItem(R.id.buoc5).setChecked(true);
                            break;
                        default:
                            bottomNavigationView.getMenu().findItem(R.id.buoc1).setChecked(true);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }


}