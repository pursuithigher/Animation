package com.qzzhu.animator.objectanimator;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

/**
 * Created by qzzhu on 16-9-19.
 *
 */
public class AnimatorUtils {
//    Duration：动画的持续时间
//
//    TimeInterPolation：属性值的计算方式，如先快后慢
//
//    TypeEvaluator：根据属性的开始，结束值与TimeInterpolation计算出的因子计算出当前时间的属性值
//
//    Repeat Count and behavoir：重复次数与方式，如播放3次，5次，无限循环，可以让此动画一直重复，或者是播放完时再反向播放。
//
//    Animation sets：动画集合，即可以同时对一个对象应用几个动画，这些动画可以同时播放也可以对不同的动画设置不同开始偏移。
//
//    Frame refresh delay：多少时间刷新一次，即每隔多长时间计算一次属性值，默认为10s，最终刷新时间还受系统进程的调度与硬件的影响

    //ObjectAnimator    ViewAnimator    AnimationSet   TypeEvalutors   TimeInterplator

    private static int DEFAULT_Duration = 500;


    public static ObjectAnimator CreateAnimator(View target,String propertyName, float... values){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target,propertyName,values).setDuration(DEFAULT_Duration);
        objectAnimator.setInterpolator(new LinearInterpolator());
        return objectAnimator;
    }

    public static ObjectAnimator CreateAnimator(View target, PropertyValuesHolder... values){
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(target,values).setDuration(DEFAULT_Duration);
        objectAnimator.setInterpolator(new LinearInterpolator());
        return objectAnimator;
    }

//    public static AnimatorSet CreateQueueAnimators(Animator... animators){
//        AnimatorSet set = new AnimatorSet();
////        set.playSequentially(animators);
////        set.playTogether(animators);
//    }

    /**
     * use View.animate() for fast create animator
     * @param target target view
     * @return only call sacle/translate/alpha/rotate to animate this target view
     */
    public static ViewPropertyAnimator TweenAnimator(View target){
        return target.animate().setDuration(DEFAULT_Duration).setInterpolator(new LinearInterpolator());
    }

    /**
     * this interface is to change special value(regardless of what property) under timeline
     * @param <T>
     */
    interface ValueEvaluator<S,T>{
        /**
         * @param Timefactor from 0% ~ 100% of time factor
         * @param var1 start value
         * @param var2 end value
         * @return
         */
        T evaluate(S target, float Timefactor,T var1, T var2);
    }

    /**
     * create a custom change of property
     * @param target object to change
     * @param evaluator time evaluate
     * @param value1 start value
     * @param value2 end value
     * @param <S> object type
     * @param <T> value type
     * @return the animator to start
     */
    public static<S,T> ValueAnimator CreateCustomAnimator(final S target,final ValueEvaluator<S,T> evaluator,T value1, T value2){
        ValueAnimator valueAnimator = ObjectAnimator.ofObject(new TypeEvaluator<T>() {
            @Override
            public T evaluate(float v, T t, T t1) {
                return evaluator.evaluate(target,v,t,t1);
            }
        },value1,value2);
        valueAnimator.setDuration(DEFAULT_Duration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        return valueAnimator;
    }
}
