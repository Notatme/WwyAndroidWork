package com.wwy.mysigninappnewest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TeacherFragmentCreate extends Fragment {
    Activity context;
    Button createQRCodeBtn;
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
        createQRCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 添加点击事件跳转二维码生成页
                Intent intent = new Intent(context, GenerateQRCodeActivity.class);
                // 传递给生成页的字符串信息
                intent.putExtra("QRCodeInfo", "Hello,World");
                startActivity(intent);
            }
        });
    }
}
