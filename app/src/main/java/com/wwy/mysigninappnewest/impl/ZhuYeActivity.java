package com.wwy.mysigninappnewest.impl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wwy.mysigninappnewest.MainActivity;
import com.wwy.mysigninappnewest.R;
import com.wwy.mysigninappnewest.pojo.Student;
import com.wwy.mysigninappnewest.pojo.Teacher;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;



public class ZhuYeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Sign;

    private EditText yanzhengcode;
    private EditText Teaname;
    private String TeanameStr;
    private String ObId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {    //这个页面用于学生签到
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ye);
        Bundle bundle=this.getIntent().getExtras();              //接收objectid
        ObId=bundle.getString("objectID");
      init();
    }

  public void init(){
      yanzhengcode=findViewById(R.id.yanzhengcode);
      Teaname = findViewById(R.id.teaname);


        Sign=findViewById(R.id.member_sigin_btn);
        Sign.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {              //查询教师表中名字为设定值，并且校验输入的的验证码

        BmobQuery<Teacher> Tquery =new BmobQuery<Teacher>();
        TeanameStr=Teaname.getText().toString();

        Tquery.addWhereEqualTo("name", TeanameStr);      //查询教师表中字段的属性值为“TeacherStr”的行

        //Toast.makeText(ZhuYeActivity.this,Teaname.getText().toString(), Toast.LENGTH_LONG).show();
        Tquery.findObjects(new FindListener<Teacher>() {
            @Override
            public void done(List<Teacher> list, BmobException e) {
                if (e == null) {

                    String code = list.get(0).getTqiandaocode().toString();
                    String inputcode = yanzhengcode.getText().toString();
                    Toast.makeText(ZhuYeActivity.this,list.get(0).getObjectId(), Toast.LENGTH_LONG).show();

                    if (code.equals(inputcode) ) {                 //判断验证码
                        Student student=new Student();
                        student.setQiandaocode(code);
                        student.update(ObId, new UpdateListener() {     //把签到码加入到数据库中

                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(ZhuYeActivity.this, "插入成功！", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(ZhuYeActivity.this, "插入失败！", Toast.LENGTH_LONG).show();
                                }
                            }

                        });

                        Toast.makeText(ZhuYeActivity.this, "验证码正确，签到成功！", Toast.LENGTH_LONG).show();


                    }

                }
                else{
                    Toast.makeText(ZhuYeActivity.this, "验证码错误，请重新输入验证码！", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}