package com.wwy.mysigninappnewest.impl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wwy.mysigninappnewest.MainActivity;
import com.wwy.mysigninappnewest.R;
import com.wwy.mysigninappnewest.pojo.Person;
import com.wwy.mysigninappnewest.pojo.Student;
import com.wwy.mysigninappnewest.pojo.Teacher;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {


    //st_number   学号
    //username_register  姓名
    //password_register   密码
    //phone_register    //手机号码
    //school_register    //学校，名称
    //register_btn    //注册

    private Button RegBtn;
    private EditText st_number;
    private EditText username_register;
    private EditText password_register;
    private EditText phone_register;
    private EditText school_register;
    private EditText major_register;
    private String sadress=null;
    private String code=null;
    private CheckBox sort;
    private ImageView fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private  void initView(){      //初始化控件获得值

        fanhui=findViewById(R.id.fanhui);
        RegBtn=findViewById(R.id.register_btn);
        st_number=findViewById(R.id.st_number);
        username_register=findViewById(R.id.username_register);
        password_register=findViewById(R.id.password_register);
        phone_register=findViewById(R.id.phone_register);
        school_register=findViewById(R.id.school_register);
        major_register=findViewById(R.id.major_register);
       sort=findViewById(R.id.cb_SorT);

       fanhui.setOnClickListener(new View.OnClickListener() {    //点击返回
           @Override
           public void onClick(View v) {
              RegisterActivity.this.finish();
           }
       });


        RegBtn.setOnClickListener(new View.OnClickListener() {       //点击注册
            @Override
            public void onClick(View v) {
if(sort.isChecked()) {
    addUsertea(st_number.getText().toString(), username_register.getText().toString(),
            password_register.getText().toString(), phone_register.getText().toString(),
            school_register.getText().toString(), major_register.getText().toString());
}
else{
    addUserstu(st_number.getText().toString(), username_register.getText().toString(),
            password_register.getText().toString(), phone_register.getText().toString(),
            school_register.getText().toString(), major_register.getText().toString());
}
            }
        });





    }


    //用于向数据库中添加学生

    public void addUserstu(String Number,String UserName,String Password,String phone,String school,String major){

        Student s=new Student();
        s.setNumber(Number);
        s.setName(UserName);
        s.setPassword(Password);
        s.setSelfNumber(phone);
        s.setSchoolname(school);
        s.setMajor(major);
        s.setQiandaocode("");
        s.setAdress("");
        s.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                        }else{
                            Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }
//添加老师
    public void addUsertea(String Number,String UserName,String Password,String phone,String school,String major){
        Teacher t=new Teacher();
        t.setNumber(Number);
        t.setName(UserName);
        t.setPassword(Password);
        t.setSelfNumber(phone);
        t.setSchoolname(school);
        t.setMajor(major);
        t.setTqiandaocode("");
        t.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                }else{
                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}