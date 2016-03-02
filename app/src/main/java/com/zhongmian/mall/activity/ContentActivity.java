package com.zhongmian.mall.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongmian.mall.R;
import com.zhongmian.mall.fragment.BrandFragment;
import com.zhongmian.mall.fragment.CartFragment;
import com.zhongmian.mall.fragment.DepartmentFragment;
import com.zhongmian.mall.fragment.HomeFragment;
import com.zhongmian.mall.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;




public class ContentActivity extends FragmentActivity {
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_content);

        initData();
        initCtrl();
        init();

    }

    private void initData() {
        fragments = new ArrayList<Fragment>();
        HomeFragment homeFragment = new HomeFragment();
        BrandFragment brandFragment = new BrandFragment();
        CartFragment cartFragment = new CartFragment();
        DepartmentFragment departmentFragment = new DepartmentFragment();
        MineFragment mineFragment = new MineFragment();
        fragments.add(homeFragment);
        fragments.add(brandFragment);
        fragments.add(departmentFragment);
        fragments.add(cartFragment);
        fragments.add(mineFragment);

    }
    private void initCtrl(){

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
    }

    private void init() {
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout_navigation);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(fragmentPagerAdapter);
        int count = linearLayout.getChildCount();
        final TextView[] textViews = new TextView[count];
        for (int i =0;i<count;i++){
            textViews[i] = (TextView) linearLayout.getChildAt(i);
            textViews[i].setEnabled(true);
            textViews[i].setTextColor(Color.GRAY);

        }
        textViews[0].setEnabled(false);
        textViews[0].setTextColor(Color.RED);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int count = linearLayout.getChildCount();
                for(int i=0;i<count;i++){
                    textViews[i].setEnabled(true);
                    textViews[i].setTextColor(Color.GRAY);
                }
                textViews[position].setEnabled(false);
                textViews[position].setTextColor(Color.RED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void onClick(View view){
        for(int i=0;i<linearLayout.getChildCount();i++){
            TextView textView = (TextView) linearLayout.getChildAt(i);
            textView.setEnabled(true);
            textView.setTextColor(Color.GRAY);
        }
        TextView textView = (TextView) view;
        textView.setEnabled(false);
        textView.setTextColor(Color.RED);
        int index = linearLayout.indexOfChild(view);
        viewPager.setCurrentItem(index);
    }

}
