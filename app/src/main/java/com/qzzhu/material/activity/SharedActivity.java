package com.qzzhu.material.activity;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;

import com.qzzhu.animation.R;

/**
 * Created by qzzhu on 16-9-18.
 *
 */
public class SharedActivity extends AppCompatActivity {

    View imageshared;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        getWindow().setEnterTransition(new Slide(Gravity.LEFT));
        getWindow().setExitTransition(new Slide(Gravity.RIGHT));




        imageshared = findViewById(R.id.imageshared);
        if(getIntent().getIntExtra("action",-1)!=-1){
            findViewById(R.id.reveal_button).setVisibility(View.VISIBLE);

            //this is more useful to queue a ViewAnimation
            getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {

                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    getWindow().getEnterTransition().removeListener(this);
                    animateBg();
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
    }


    /**
     * reveal a imageView
     * @param view
     */
    public void reveal(View view){
        Animator animator = ViewAnimationUtils.createCircularReveal(imageshared,0,0,0, (float) Math.hypot(imageshared.getWidth(),imageshared.getHeight()));
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(800);
        animator.start();
    }

    /**
     * reveal a view group
     */
    private void animateBg(){
        View imageshared =findViewById(R.id.content);
        Animator animator = ViewAnimationUtils.createCircularReveal(imageshared,imageshared.getWidth()/2,imageshared.getHeight(),0, (float) Math.hypot(imageshared.getWidth(),imageshared.getHeight()));
        imageshared.setBackgroundColor(Color.parseColor("#CCCCCC"));
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(800);
        animator.start();
    }
}
