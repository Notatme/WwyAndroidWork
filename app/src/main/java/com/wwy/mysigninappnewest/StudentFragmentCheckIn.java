package com.wwy.mysigninappnewest;

import android.Manifest;
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
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;
import com.wwy.mysigninappnewest.impl.ZhuYeActivity;

import pub.devrel.easypermissions.EasyPermissions;

public class StudentFragmentCheckIn extends Fragment {
    private Activity context;
    private Button scanQRCodeBtn;
    private Button numberCodeBtn;
    private String title;
    private boolean isContinuousScan;

    @Nullable
    @Override
    // 创建fragmeng视图
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.student_fragment_check_in, container, false);
    }

    @Override
    // 当activity创建时,添加二维码扫描点击事件
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        scanQRCodeBtn = (Button) context.findViewById(R.id.btn_scan_qrcode);
        numberCodeBtn= (Button) context.findViewById(R.id.btn_number_qrcode);
        scanQRCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] perms = {Manifest.permission.CAMERA};
                // 判断应用是否有拍摄权限
                if (EasyPermissions.hasPermissions(context, perms)) {
                    // 启动二维码扫描
                    ActivityOptionsCompat optionsCompat
                            = ActivityOptionsCompat.makeCustomAnimation(context, 18,4);
                    Intent intent = new Intent(context, CaptureActivity.class);
                    intent.putExtra("key_title", title);
                    intent.putExtra("key_continuous_scan", isContinuousScan);
                    ActivityCompat.startActivityForResult(context, intent, 1, optionsCompat.toBundle());
                } else {
                    EasyPermissions.requestPermissions(context, "App扫码需要拍摄权限", 1, perms);
                }
            }
        });


        numberCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ZhuYeActivity.class);
                startActivity(intent);
            }
        });

    }
}
