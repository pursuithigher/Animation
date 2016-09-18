package com.qzzhu.animation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;

import com.qzzhu.animation.R;

/**
 * Created by qzzhu on 16-9-18.
 */

public class SampleActivity extends AppCompatActivity{
    public final static int FADE=0;
    public final static int SLIDE=1;
    public final static int EXPLODE=2;
    public final static String ATCION = "action";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_transition);
        int params = getIntent().getIntExtra(ATCION,2);
        setUpAnim(params);
    }

    public void setUpAnim(int upAnim) {
        switch(upAnim)
        {
            case FADE:
                Fade fade = new Fade();
                fade.setDuration(800);
                setTransition(fade);
                break;
            case SLIDE:
                Slide slide = new Slide(Gravity.BOTTOM);
                slide.setDuration(1000);
                setTransition(slide);
                break;
            case EXPLODE:
                Explode explode = new Explode();
                explode.setDuration(1200);
                setTransition(explode);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAfterTransition();
    }

    private void setTransition(Transition transition){
        getWindow().setEnterTransition(transition);
        getWindow().setReturnTransition(transition);
    }
}
