package com.qzzhu.animation.mian;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.qzzhu.animation.R;
import com.qzzhu.animation.activity.SharedActivity;
import com.qzzhu.animation.activity.TransitionActivity;

/**
 * Created by qzzhu on 16-9-18.
 * main page
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    com.qzzhu.animation.MainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mian);
        binding.setClickListener(this);
        setUpAnim();
    }

    private void setUpAnim() {
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(500);
        //返回时该页面时该页面的动画
        getWindow().setReenterTransition(slideTransition);
        //该页面退出时的动画
        getWindow().setExitTransition(slideTransition);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch(id)
        {
            case R.id.transition:
                startTransitionActivity(view);
                break;
            case R.id.sharelements:
                startSharedActivity(false);
                break;
            case R.id.reveal:
                startSharedActivity(true);
                break;
        }
    }

    /**
     * 使用ActivityOptionsCompat.makeSceneTransitionAnimation创建动画元素
     * @param view useless
     */
    private void startTransitionActivity(View view){
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent i = new Intent(this,TransitionActivity.class);
        startActivity(i,activityOptionsCompat.toBundle());
    }

    /**
     * 跳转的Activity里面加载的xml必须要有View有相同的name
     * 即pair里面第二个参数与xml里面有android:transitionName="@string/*"对应
     */
    private void startSharedActivity(boolean canreveal){
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this,new Pair<View, String>(binding.title,getResources().getString(R.string.sharedtitle))
                ,new Pair<View, String>(binding.iamge,getResources().getString(R.string.sharedimage)));
        Intent i = new Intent(this,SharedActivity.class);
        if(canreveal)
        {
            i.putExtra("action",0);
        }
        startActivity(i,activityOptionsCompat.toBundle());
    }
}
