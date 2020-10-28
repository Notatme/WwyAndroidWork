package com.wwy.mysigninappnewest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.king.zxing.Intents;

public class StudentHomeActivity extends AppCompatActivity {
    ActionBar actionBar;
    // 底部导航栏
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity_home);

        initTab();
        // 手动触发一次tab切换事件
        tabLayout.selectTab(tabLayout.getTabAt(1));
        tabLayout.selectTab(tabLayout.getTabAt(0));
    }

    protected void initTab() {
        tabLayout = findViewById(R.id.nav_bar_student);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("onTabSelected: ", String.valueOf(position));

                // 切换图标颜色 ufo_green #2ed573
                tabLayout.getTabAt(position)
                        .getIcon()
                        .setColorFilter(Color.parseColor("#2ed573"), PorterDuff.Mode.SRC_IN);

                switch (position) {
                    case 0:
                        Log.d("check in fragment", "FragmentCreate");
                        changeFragment(R.id.empty_fragment_student, new StudentFragmentCheckIn());
                        break;
                    case 1:
                        Log.d("record fragment", "FragmentRecord");
                        changeFragment(R.id.empty_fragment_student, new StudentFragmentRecord());
                        break;
                    case 2:
                        Log.d("more fragment", "FragmentMore");
                        changeFragment(R.id.empty_fragment_student, new StudentFragmentMore());
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

    protected void changeFragment(int changeLayoutId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(changeLayoutId, fragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 1:
                    String result = data.getStringExtra(Intents.Scan.RESULT);
                    Toast.makeText(StudentHomeActivity
                            .this, result, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
