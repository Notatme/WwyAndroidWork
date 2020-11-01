package com.wwy.mysigninappnewest;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
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

    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    public static String address;

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

        /*
        定位模块
         */
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        //mLocationClient.setLocationListener(mapLocationListener);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        AMapLocationListener mapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if(aMapLocation != null){
                    if(aMapLocation.getErrorCode() == 0){
                        StringBuffer buffer = new StringBuffer(); //定义位置信息存储
                        buffer.append(aMapLocation.getCountry() + ""
                                + aMapLocation.getProvince() + ""
                                + aMapLocation.getCity() + ""
                                + aMapLocation.getProvince() + ""
                                + aMapLocation.getDistrict() + ""
                                + aMapLocation.getStreet() + ""
                                + aMapLocation.getStreetNum());
                        address = buffer.toString();
                        //Toast.makeText(context, buffer.toString(), Toast.LENGTH_LONG).show();
                    }else {
                        Log.e("AmapError","location Error, ErrCode:"+ aMapLocation.getErrorCode()
                                + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        };
        mLocationClient.setLocationListener(mapLocationListener);
        //停止定位
        //mLocationClient.stopLocation();


        scanQRCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] perms = {Manifest.permission.CAMERA};
                if (EasyPermissions.hasPermissions(context, perms)) {
                    // 判断应用是否有拍摄权限
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
