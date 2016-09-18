package com.qzzhu.animation.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qzzhu.animation.R;
import com.qzzhu.animation.TransitBinding;

/**
 * Created by qzzhu on 16-9-18.
 * simple scene animation such as fade,explode and slide
 */
public class TransitionActivity extends AppCompatActivity implements View.OnClickListener {

    TransitBinding binding = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_transition);
        binding.setClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fade:
                startActivity(SampleActivity.FADE);
                break;
            case R.id.explode:
                startActivity(SampleActivity.EXPLODE);
                break;
            case R.id.slide:
                startActivity(SampleActivity.SLIDE);
                break;
        }
    }

    /**
     * ActivityOptionsCompat.makeSceneTransitionAnimation is necessary
     * call startActivity (Intent intent, Bundle options) to start a effective activity
     * @param action ways how to transit
     */
    private void startActivity(int action)
    {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent i = new Intent(this,SampleActivity.class);
        i.putExtra(SampleActivity.ATCION,action);
        startActivity(i,activityOptionsCompat.toBundle());
    }
}
