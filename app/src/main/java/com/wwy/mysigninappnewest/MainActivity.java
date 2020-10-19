package com.wwy.mysigninappnewest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wwy.mysigninappnewest.buju.JellyInterpolator;
import com.wwy.mysigninappnewest.impl.RegisterActivity;
import com.wwy.mysigninappnewest.impl.ZhuYeActivity;
import com.wwy.mysigninappnewest.pojo.Person;
import com.wwy.mysigninappnewest.pojo.Student;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {


    private TextView mBtnLogin;

    private  TextView mBtnResigter;

    private View progress;

    private View mInputLayout;

    private float mWidth, mHeight;

    private LinearLayout mName, mPsw;
    private EditText username;
    private EditText upassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "397e951a8c743debcc77f678ccad8e87");
        mName = (LinearLayout) findViewById(R.id.input_layout_name);
        mPsw = (LinearLayout) findViewById(R.id.input_layout_psw);
        initView();

    }
    private void initView() {
        mBtnLogin = (TextView) findViewById(R.id.main_btn_login);
        mBtnResigter = (TextView) findViewById(R.id.main_btn_register);
        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);
        username = findViewById(R.id.input_username);
        upassword=findViewById(R.id.input_userpassword);
        mBtnLogin.setOnClickListener(this);
        mBtnResigter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);



            }
        });
    }
    @Override
    public void onClick(View v) {

        // 计算出控件的高与宽
        mWidth = mBtnLogin.getMeasuredWidth();
        mHeight = mBtnLogin.getMeasuredHeight();
        // 隐藏输入框
        mName.setVisibility(View.INVISIBLE);
        mPsw.setVisibility(View.INVISIBLE);

        inputAnimator(mInputLayout, mWidth, mHeight);

        String name=username.getText().toString();
        String password=upassword.getText().toString();

        if(name.equals("")||password.equals("")){
            Toast.makeText(this, "帐号或密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        BmobQuery<Student> query=new BmobQuery<Student>();
        query.addWhereEqualTo("selfNumber", name);              //这里使用手机号码登录
        query.addWhereEqualTo("password", password);
query.findObjects(new FindListener<Student>() {
    @Override
    public void done(List<Student> list, BmobException e) {
       if(e==null){
           String gname=list.get(0).getSelfNumber().toString();
           String gpassword = list.get(0).getPassword().toString();

           String name=username.getText().toString();
           String password=upassword.getText().toString();

           if(gname.equals(name)&&gpassword.equals(password))
           {
               Intent seccess = new Intent();
//               seccess.setClass(MainActivity.this, ThridActivity.class);
               startActivity(new Intent(MainActivity.this,ZhuYeActivity.class));
               Toast.makeText(MainActivity.this, "验证成功", Toast.LENGTH_LONG).show();
               startActivity(seccess);
           }

       }
       else{
           Toast.makeText(MainActivity.this, "帐号或密码有误", Toast.LENGTH_LONG).show();
       }


}
});



    }





        /**
         * 输入框的动画效果
         *
         * @param view
         *      控件
         * @param w
         *      宽
         * @param h
         *      高
         */
        private void inputAnimator(final View view, float w, float h) {

            AnimatorSet set = new AnimatorSet();

            ValueAnimator animator = ValueAnimator.ofFloat(0, w);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (Float) animation.getAnimatedValue();
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                            .getLayoutParams();
                    params.leftMargin = (int) value;
                    params.rightMargin = (int) value;
                    view.setLayoutParams(params);
                }
            });

            ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                    "scaleX", 1f, 0.5f);
            set.setDuration(1000);
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.playTogether(animator, animator2);
            set.start();
            set.addListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    /**
                     * 动画结束后，先显示加载的动画，然后再隐藏输入框
                     */
                    progress.setVisibility(View.VISIBLE);
                    progressAnimator(progress);
                    mInputLayout.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }
            });

        }



    /**
     * 出现进度动画
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(1000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }

}