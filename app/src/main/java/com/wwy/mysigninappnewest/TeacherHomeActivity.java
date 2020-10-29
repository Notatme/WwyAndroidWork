package com.wwy.mysigninappnewest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class TeacherHomeActivity extends AppCompatActivity {
    ActionBar actionBar;
    // 底部导航栏
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_activity_home);

        // 初始化tab切换事件
        initTab();
        // 手动触发一次tab切换事件
        tabLayout.selectTab(tabLayout.getTabAt(1));
        tabLayout.selectTab(tabLayout.getTabAt(0));
    }

    protected void initTab() {
        tabLayout = findViewById(R.id.nav_bar);

        // 添加tab切换事件
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("onTabSelected: ", String.valueOf(position));

                // 切换图标颜色 ufo_green #2ed573
                tabLayout.getTabAt(position)
                        .getIcon()
                        .setColorFilter(Color.parseColor("#2ed573"), PorterDuff.Mode.SRC_IN);

                // 判断现在tab的位置，选择对应的tab页
                switch (position) {
                    case 0:
                        Log.d("create fragment", "FragmentCreate");
                        // 教师生成签到tab页
                        changeFragment(R.id.empty_fragment, new TeacherFragmentCreate());
                        break;
                    case 1:
                        Log.d("record fragment", "FragmentRecord");
                        // 教师查看签到记录tab页
                        changeFragment(R.id.empty_fragment, new TeacherFragmentRecord());
                        break;
                    case 2:
                        Log.d("more fragment", "FragmentMore");
                        // 教师更多信息tab页
                        changeFragment(R.id.empty_fragment, new TeacherFragmentMore());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("onTabUnselected: ", String.valueOf(position));

                // 切换图标颜色 peace #a4b0be
                tabLayout.getTabAt(position)
                        .getIcon()
                        .setColorFilter(Color.parseColor("#a4b0be"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // 切换tab页函数
    protected void changeFragment(int changeLayoutId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(changeLayoutId, fragment)
                .commit();
    }
}