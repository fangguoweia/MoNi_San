package com.bwei.MoNi_San.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.bwei.MoNi_San.R;

public class DongHuaActivity extends AppCompatActivity {

    private Button alpha;
    private ImageView animationv;
    private Button rotate;
    private Button translate;
    private Button scale;
    private Button zuhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_hua);
        animationv = findViewById(R.id.iv);
        alpha = findViewById(R.id.alpha);
        rotate = findViewById(R.id.rotate);
        translate = findViewById(R.id.translate);
        scale = findViewById(R.id.scale);
        zuhe = findViewById(R.id.zuhe);
        /**
         * 组合动画
         */
        zuhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //沿x轴放大
                ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(animationv, "scaleX", 1f, 2f, 1f);
                //沿y轴放大
                ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(animationv, "scaleY", 1f, 2f, 1f);
                //移动
                ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(animationv, "translationX", 0f, 200f, 0f);
                //透明动画
                ObjectAnimator animator = ObjectAnimator.ofFloat(animationv, "alpha", 1f, 0f, 1f);

                //在x和y轴缩放动画结束之后，执行平移动画，在平移的同时，让此控件进行渐变
                AnimatorSet set = new AnimatorSet();
                set.play(scaleXAnimator).with(scaleYAnimator).before(translationXAnimator).with(animator);
                set.setDuration(5000);
                set.start();
            }
        });
        /**
         * 渐变
         */
        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(animationv, "alpha", 1f, 0.1f, 1f, 0.5f, 1f);
                alpha.setDuration(6000);
                alpha.start();
            }
        });
        /**
         * 缩放
         */
        scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSetsuofang = new AnimatorSet();//组合动画
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(animationv, "scaleX", 0, 1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(animationv, "scaleY", 0, 1f);

                animatorSetsuofang.setDuration(2000);
                animatorSetsuofang.setInterpolator(new DecelerateInterpolator());
                animatorSetsuofang.play(scaleX).with(scaleY);//两个动画同时开始
                animatorSetsuofang.start();
            }
        });
        /**
         * 平移
         */
        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator translationX = new ObjectAnimator().ofFloat(animationv,"translationX",0,400f,0);
                ObjectAnimator translationY = new ObjectAnimator().ofFloat(animationv,"translationY",0,0);

                AnimatorSet animatorSet = new AnimatorSet();  //组合动画
                animatorSet.playTogether(translationX); //设置动画
                animatorSet.setDuration(3000);  //设置动画时间
                animatorSet.start(); //启动
            }
        });
        /**
         * 旋转
         */
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator rotation = ObjectAnimator.ofFloat(animationv, "rotation",  0f, 360f);
                rotation.setDuration(3000);
                rotation.start();
                //↓ 暂时用不着
                /*rotation.setRepeatCount(-1);
                //从开始位置循环还是从结束位置循环
                rotation.setRepeatMode(ValueAnimator.REVERSE);
                rotation.setRepeatMode(ValueAnimator.RESTART);
                rotation.cancel();
                rotation.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //开始
                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //动画结束跳转
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        //取消
                    }
                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        //循环执行
                    }
                });*/
            }
        });
    }

}
