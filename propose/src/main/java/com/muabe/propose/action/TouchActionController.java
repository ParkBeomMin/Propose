package com.muabe.propose.action;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.muabe.propose.TouchDetector;
import com.muabe.propose.combination.combiner.ActionModule;
import com.muabe.propose.touch.detector.OnTouchDetectListener;
import com.muabe.propose.touch.detector.multi.MultiTouchEvent;
import com.muabe.propose.touch.detector.single.SingleTouchEvent;

/**
 * <br>捲土重來<br>
 *
 * @author 오재웅(JaeWoong - Oh)
 * @email markjmind@gmail.com
 * @since 2018-10-15
 */
public class TouchActionController extends ActionModule implements View.OnTouchListener, OnTouchDetectListener {
    private TouchDetector touchDetector;
    private SingleTouchAction singleTouchAction = new SingleTouchAction();
    private MultiTouchAction multiTouchAction = new MultiTouchAction();
    private Context context;
    private boolean isMulti;

    public TouchActionController(Context context, boolean isMulti){
        this.context = context;
        this.isMulti = isMulti;
    }


    @Override
    public void bind(OnActionListener listener) {
        touchDetector = new TouchDetector(context, this);
        singleTouchAction.bind(listener);
        multiTouchAction.bind(listener);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return touchDetector.onTouchEvent(view, motionEvent);
    }

    @Override
    public boolean onDown(SingleTouchEvent event) {
        return false;
    }

    @Override
    public boolean onUp(SingleTouchEvent event) {
        return false;
    }

    @Override
    public boolean onDrag(SingleTouchEvent event) {
        return singleTouchAction.onDrag(event);
    }

    @Override
    public boolean onMulitBegin(MultiTouchEvent event) {
        return true;
    }

    @Override
    public boolean onMultiEnd(MultiTouchEvent event) {
        return false;
    }

    @Override
    public boolean onMultiDrag(MultiTouchEvent event) {
        return multiTouchAction.onMultiDrag(event);
    }

    @Override
    public boolean onMultiUp(MultiTouchEvent multiEvent) {
        return false;
    }
}
