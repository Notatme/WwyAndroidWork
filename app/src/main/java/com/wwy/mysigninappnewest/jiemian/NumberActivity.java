package com.wwy.mysigninappnewest.jiemian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wwy.mysigninappnewest.R;

public class NumberActivity extends AppCompatActivity implements View.OnClickListener {

    private String Number;
    private TextView TextNumber;
    private ImageView fanhui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        Bundle bundle=this.getIntent().getExtras();              //接收objectid
        Number=bundle.getString("number");
        TextNumber=findViewById(R.id.textView1);
        fanhui=findViewById(R.id.fanhui);
        TextNumber.setText(Number);
        fanhui.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        NumberActivity.this.finish();
    }
}