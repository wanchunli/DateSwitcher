package com.wan.grace.dateswitcher;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by 开发部 on 2017/12/28.
 */

public class ScrollTextView extends AppCompatTextView {

    public static final float LIMIT_DISTANCE = 50f;
    private OnScrollChangeListener changeListener;

    public ScrollTextView(Context context) {
        super(context);
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float distance = 0f;
        float startY = 0f;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
//                Log.i("startY",startY+"");
                break;
            case MotionEvent.ACTION_MOVE:
                float scrollY = event.getY();
                distance = scrollY - startY;
                Log.i("scrollY",scrollY+"");
                Log.i("DownY",startY+"");
                break;
            case MotionEvent.ACTION_UP:
                float endY = event.getY();
                distance = endY - startY;
                Log.i("distance",distance+"");
                if (distance > LIMIT_DISTANCE) {
                    if (getOnScrollChangeListener() != null) {
                        getOnScrollChangeListener().scrollDown();
                    }
                } else if (distance < -LIMIT_DISTANCE) {
                    if (getOnScrollChangeListener() != null) {
                        getOnScrollChangeListener().scrollUp();
                    }
                }
                break;
        }
        return true;
    }

    public void setOnScrollChangeListener(OnScrollChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public OnScrollChangeListener getOnScrollChangeListener() {
        return changeListener;
    }

    public interface OnScrollChangeListener {
        //向上滑动
        void scrollUp();

        //向下滑动
        void scrollDown();
    }
}
