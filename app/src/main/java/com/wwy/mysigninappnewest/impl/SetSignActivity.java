package com.wwy.mysigninappnewest.impl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wwy.mysigninappnewest.R;
import com.wwy.mysigninappnewest.pojo.Student;
import com.wwy.mysigninappnewest.pojo.Teacher;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


//这个页面用于老师设置签到码
public class SetSignActivity extends AppCompatActivity implements View.OnClickListener {
private Button SetCodeBtn;
private EditText Code;
private String ObId;
private String CCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_sign);
        Bundle bundle=this.getIntent().getExtras();              //接收从MainActivity到来的objectid
        ObId=bundle.getString("objectID");
        init();
        SetCodeBtn.setOnClickListener(this);

    }

    public void init(){
        Code=findViewById(R.id.Setcode);
        SetCodeBtn=findViewById(R.id.siginCode);
//xdcfcggcgcvbhvhvh

    }

    @Override
    public void onClick(View v) {
        CCode=Code.getText().toString();
        Teacher teacher=new Teacher();
        teacher.setTqiandaocode(CCode);
        teacher.update(ObId, new UpdateListener() {     //把签到码加入到数据库中

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(SetSignActivity.this, "设置签到码成功！", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SetSignActivity.this, "设置签到码失败！", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
}