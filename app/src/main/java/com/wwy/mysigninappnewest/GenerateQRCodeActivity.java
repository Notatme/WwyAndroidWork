package com.wwy.mysigninappnewest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.king.zxing.util.CodeUtils;

public class GenerateQRCodeActivity extends AppCompatActivity {
    private ImageView ivCode;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        ivCode = findViewById(R.id.ivCode);
        // 传递字符串，生成二维码
        createQRCode(getIntent().getStringExtra("QRCodeInfo"));
    }

    private void createQRCode(String content){
        Log.d("start createQRCode", "***************true**************");
        new Thread(() -> {
            //生成二维码相关放在子线程里面
            Bitmap bitmap =  CodeUtils.createQRCode(content,600);
            Log.d("createQRCode: ", bitmap.toString());
            runOnUiThread(()->{
                //显示二维码
                ivCode.setImageBitmap(bitmap);
            });
        }).start();
    }
}
