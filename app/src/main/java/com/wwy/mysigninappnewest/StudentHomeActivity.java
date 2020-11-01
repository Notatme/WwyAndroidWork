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
import com.wwy.mysigninappnewest.impl.Data;
import com.wwy.mysigninappnewest.impl.ZhuYeActivity;
import com.wwy.mysigninappnewest.pojo.Student;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class StudentHomeActivity extends AppCompatActivity {
    ActionBar actionBar;
    // 底部导航栏
    private TabLayout tabLayout;
    private String ObId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity_home);

        // 初始化tab切换事件
        initTab();
        // 手动触发一次tab切换事件
        tabLayout.selectTab(tabLayout.getTabAt(1));
        tabLayout.selectTab(tabLayout.getTabAt(0));
    }

    protected void initTab() {
        tabLayout = findViewById(R.id.nav_bar_student);
//<<<<<<< Updated upstream
        // 添加tab切换事件
//=======
       // btn_scan_qrcode


//>>>>>>> Stashed changes
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
                        Log.d("check in fragment", "FragmentCreate");
                        // 学生签到tab页
                        changeFragment(R.id.empty_fragment_student, new StudentFragmentCheckIn());
                        break;
                    case 1:
                        Log.d("record fragment", "FragmentRecord");
                        // 学生签到记录查看tab页
                        changeFragment(R.id.empty_fragment_student, new StudentFragmentRecord());
                        break;
                    case 2:
                        Log.d("more fragment", "FragmentMore");
                        // 更多信息tab页
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

    // 切换tab页函数
    protected void changeFragment(int changeLayoutId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(changeLayoutId, fragment)
                .commit();
    }

    // 由于fragment不能接受activity传回的结果
    // 二维码传回的值由fragment附在的activity接受
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {      //扫描二维码返回的结果
        super.onActivityResult(requestCode, resultCode, data);

        StudentFragmentCheckIn stu = new StudentFragmentCheckIn();

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 1:
                    // 获取二维码扫描的值
                    String result = data.getStringExtra(Intents.Scan.RESULT);
                    //Toast.makeText(StudentHomeActivity.this, "签到成功", Toast.LENGTH_SHORT).show();
                    Student student=new Student();
                    student.setQiandaocode(result);
                    student.setAdress(stu.address);
                    student.update(Data.getObjectId(), new UpdateListener() {     //把签到码加入到数据库中

                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(StudentHomeActivity.this, "签到成功！", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(StudentHomeActivity.this, "签到失败！", Toast.LENGTH_LONG).show();
                            }
                        }

                    });

                    //Toast.makeText(StudentHomeActivity.this, "验证码正确，签到成功！", Toast.LENGTH_LONG).show();

                    break;
            }
        }
    }
}
