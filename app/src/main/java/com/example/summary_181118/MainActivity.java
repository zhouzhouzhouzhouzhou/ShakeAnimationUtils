package com.example.summary_181118;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by 佳南 on 2018/11/18.
 */

public class MainActivity extends AppCompatActivity {
    private ImageView shake_iv;
    private Button btn_shakeByView, btn_shakeByProperty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        shake_iv = (ImageView) findViewById(R.id.shake_iv);
        btn_shakeByProperty = (Button) findViewById(R.id.btn_shakeByProperty);
        btn_shakeByProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShakeByPropertyAnim(shake_iv, 0.9f, 1.1f, 10f, 1000);
            }
        });
        btn_shakeByView = (Button) findViewById(R.id.btn_shakeByAnim);
        btn_shakeByView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShakeByViewAnim(shake_iv, 0.9f, 1.1f, 10f, 1000);
            }
        });


    }

    private void startShakeByViewAnim(View view, float scaleSmall, float scaleLarge, float shakeDegree, long duration) {
        if (view == null) {
            return;
        }
        Animation scaleAnim = new ScaleAnimation(scaleSmall, scaleLarge, scaleSmall, scaleLarge);
        Animation rotateAnim = new RotateAnimation(-shakeDegree, shakeDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(duration);
        rotateAnim.setDuration(duration / 10);
        rotateAnim.setRepeatMode(Animation.REVERSE);
        rotateAnim.setRepeatCount(10);

        //将设置的动画装到集合里面去
        AnimationSet smallAnimation = new AnimationSet(false);
        smallAnimation.addAnimation(scaleAnim);
        smallAnimation.addAnimation(rotateAnim);

        view.startAnimation(smallAnimation);
    }

    private void startShakeByPropertyAnim(View view, float scaleSmall, float scaleLarge, float shakeDegree, long duration) {
        if (view == null) {
            return;
        }
        PropertyValuesHolder scaleXValueHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f));


        PropertyValuesHolder scaleYValueHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1.0f),
                Keyframe.ofFloat(0.25f, scaleSmall),
                Keyframe.ofFloat(0.5f, scaleLarge),
                Keyframe.ofFloat(0.75f, scaleLarge),
                Keyframe.ofFloat(1.0f, 1.0f));

        PropertyValuesHolder rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, -shakeDegree),
                Keyframe.ofFloat(0.2f, shakeDegree),
                Keyframe.ofFloat(0.3f, -shakeDegree),
                Keyframe.ofFloat(0.4f, shakeDegree),
                Keyframe.ofFloat(0.5f, -shakeDegree),
                Keyframe.ofFloat(0.6f, shakeDegree),
                Keyframe.ofFloat(0.7f, -shakeDegree),
                Keyframe.ofFloat(0.8f, -shakeDegree),
                Keyframe.ofFloat(0.9f, shakeDegree),
                Keyframe.ofFloat(1.0f, 0f)
        );

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleXValueHolder, scaleYValueHolder, rotateValuesHolder);
        objectAnimator.setDuration(duration);
        objectAnimator.start();
    }


}
