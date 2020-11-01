package com.wwy.mysigninappnewest.impl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;
import com.wwy.mysigninappnewest.MainActivity;
import com.wwy.mysigninappnewest.R;
import com.wwy.mysigninappnewest.pojo.Student;
import com.wwy.mysigninappnewest.pojo.Teacher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;



public class ZhuYeActivity extends AppCompatActivity implements View.OnClickListener, LocationSource, AMapLocationListener {

    private Button Sign;

    private EditText yanzhengcode;
    private EditText Teaname;
    private String TeanameStr;
    private String ObId;

    private String address;

    //AMap是地图对象
    private AMap aMap;
    private MapView mapView;
    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //声明mListener对象，定位监听器
    private LocationSource.OnLocationChangedListener mListener = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {    //这个页面用于学生签到
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ye);
        //Bundle bundle=this.getIntent().getExtras();              //接收objectid
       // ObId=bundle.getString("objectID");

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if(aMap == null){
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        location();

      init();
    }

    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
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
                        student.setAdress(address);
                        student.update(Data.getObjectId(), new UpdateListener() {     //把签到码加入到数据库中

                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                   // Toast.makeText(ZhuYeActivity.this, "插入成功！", Toast.LENGTH_LONG).show();
                                    Toast.makeText(ZhuYeActivity.this, "验证码正确，签到成功！", Toast.LENGTH_LONG).show();
                                    ZhuYeActivity.this.finish();

                                }else{
                                    Toast.makeText(ZhuYeActivity.this, "签到失败！", Toast.LENGTH_LONG).show();
                                }
                            }

                        });




                    }

                }
                else{
                    Toast.makeText(ZhuYeActivity.this, "验证码错误，请重新输入验证码！", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                //aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer(); //定义位置信息存储
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    address = buffer.toString();
                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                }


            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }
}