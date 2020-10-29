package com.wwy.mysigninappnewest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wwy.mysigninappnewest.impl.Data;
import com.wwy.mysigninappnewest.impl.SetSignActivity;
import com.wwy.mysigninappnewest.jiemian.NumberActivity;
import com.wwy.mysigninappnewest.pojo.Teacher;

import java.util.UUID;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class TeacherFragmentCreate extends Fragment {
    Activity context;
    Button createQRCodeBtn;
    private Button createNumberBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.teacher_fragment_create, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        createQRCodeBtn = (Button) context.findViewById(R.id.btn_create_qrcode);
        createNumberBtn=(Button) context.findViewById(R.id.btn_create_Numbercode);
        createQRCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //Updated upstream
                // 添加点击事件跳转二维码生成页
                //Intent intent = new Intent(context, GenerateQRCodeActivity.class);
                // 传递给生成页的字符串信息
                //intent.putExtra("QRCodeInfo", "Hello,World");


                Data.setQRCode(UUID.randomUUID().toString().replace("-", ""));     //使用UUID生成随机字符串
                Teacher teacher=new Teacher();
                teacher.setTqiandaocode(Data.getQRCode());
                teacher.update(Data.getObjectId(), new UpdateListener() {     //把签到码加入到数据库中

                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                           // Toast.makeText(SetSignActivity.this, "设置签到码成功！", Toast.LENGTH_LONG).show();
                           // Intent seccess=new Intent();
                            //seccess.setClass(SetSignActivity.this, NumberActivity.class);
                           // Bundle bundle = new Bundle();     //简单参数传递objectId
                            //bundle.putString("number",CCode);
                            //seccess.putExtras(bundle);
                            //startActivity(seccess);
                            //SetSignActivity.this.finish();
                            Intent intent = new Intent(context, GenerateQRCodeActivity.class);
                            intent.putExtra("QRCodeInfo", Data.getQRCode());
                            startActivity(intent);
                            //Toast.makeText(TeacherFragmentCreate.this, "设置签到码失败！", Toast.LENGTH_LONG).show();

                        }else{
                           // Toast.makeText(TeacherFragmentCreate.this, "设置签到码失败！", Toast.LENGTH_LONG).show();
                           // Toast.makeText(TeacherFragmentCreate.this, "帐号或密码有误", Toast.LENGTH_LONG).show();
                        }
                    }

                });





            }
        });
        createNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SetSignActivity.class);
               // intent.putExtra("QRCodeInfo", "Hello,World");
//>>>>>>> Stashed changes
                startActivity(intent);
            }
        });

    }
}
