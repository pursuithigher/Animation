package com.qzzhu.animator.tweenanimation;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by qzzhu on 16-9-19.
 * tween animation
 * Tween Animation动画只是改变View的显示效果而已，但是不会真正的去改变View的属性
 */
public class AnimationUtils {

    public static Animation GetAnimationFromXML(Context context, @AnimRes int resid){
        return android.view.animation.AnimationUtils.loadAnimation(context, resid);
    }

    public static Animation CreateSacledAnimation(float fromX, float toX,
                                                  float fromY, float toY,
                                                  int pivotXType, float pivotXValue,
                                                  int pivotYType, float pivotYValue){
        return new ScaleAnimation(fromX, toX, fromY, toY,
                pivotXType, pivotXValue, pivotYType, pivotYValue);
    }

    public static Animation CreateTranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta){
        return new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
    }

    public static Animation CreateRotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue){
        return new RotateAnimation(fromDegrees, toDegrees,
                pivotXType,pivotXValue,pivotYType, pivotYValue);
    }

    public static Animation CreateAplhaAnimation(float fromAlpha, float toAlpha){
        return new AlphaAnimation(fromAlpha,toAlpha);
    }
}
