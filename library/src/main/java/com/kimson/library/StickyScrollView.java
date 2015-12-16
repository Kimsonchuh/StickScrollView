package com.kimson.library;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Scroller;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhujianheng on 12/16/15.
 */
public class StickyScrollView extends ScrollView {
    public static final String TAG = StickyScrollView.class.getSimpleName();
    public static final int PAGE_TOP = 0;
    public static final int PAGE_BOTTOM = 1;
    public static final double PERCENT = 0.25;

    private ViewGroup mChildLayout;
    private View mTopChildView;
    private View mBottomChildView;


    private Scroller mScroller;             //滑动类
    private int downY;
    private int moveY;
    private int upY;
    private int screenHeight;               //屏幕高度
    private int offsetDistance;             //topview的高度与屏幕的高度差
    private int topChildHeight;             //topview的高度
    private int bottomChildHeight;          //bottomview的高度
    private boolean isTouch;                //用户是否在触控屏幕
    private int currentPage;                //值为0时屏幕显示topview，值为1时屏幕显示bottomview

    private long downTime;
    private long upTime;
    private long duration;
    private int moveDistance;

    private Timer timer = new Timer();
    private TimerTask task;


    public StickyScrollView(Context context) {
        super(context);
        init(context);
    }

    public StickyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StickyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mChildLayout = (ViewGroup) getChildAt(0);
        mTopChildView = mChildLayout.getChildAt(0);
        mBottomChildView = mChildLayout.getChildAt(1);
        topChildHeight = mTopChildView.getMeasuredHeight();
        bottomChildHeight = mBottomChildView.getMeasuredHeight();
        screenHeight = getMeasuredHeight();
        offsetDistance = topChildHeight - screenHeight;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                downY = (int) ev.getY();
                downTime = Calendar.getInstance().getTimeInMillis();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                upY = (int) ev.getY();
                upTime = Calendar.getInstance().getTimeInMillis();
                duration = upTime - downTime;
                moveDistance = upY - downY;
                if (currentPage == PAGE_TOP) {
                    //下面的判断已经能确定用户是否往上滑
                    if (getScrollY() > offsetDistance) {
                        if (getScrollY() < (screenHeight * PERCENT + offsetDistance)) {
                            scrollToDistance(offsetDistance);

                        } else {
                            scrollToDistance(topChildHeight);
                            //切换到下界面
                            currentPage = PAGE_BOTTOM;
                            return false;
                        }
                    }
                } else {
                    if (getScrollY() < topChildHeight) {
                        if (getScrollY() < (topChildHeight - screenHeight * PERCENT)) {
                            scrollToDistance(offsetDistance);
                            //切换到上界面
                            currentPage = PAGE_TOP;
                            return false;
                        } else {
                            scrollToDistance(topChildHeight);
                        }
                    }
                }

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void scrollToDistance(final int distance) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //使用该方法时主要要使用新线程
                scrollTo(0, distance);
            }
        }, 50);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //滚动时的监听,当用户触屏滑动时不监听，t == getScrollY
        if (currentPage == PAGE_TOP) {
            if (getScrollY() > offsetDistance && !isTouch) {
                scrollTo(0, offsetDistance);
            }
        } else if (currentPage == PAGE_BOTTOM){
            if (getScrollY() < topChildHeight && !isTouch) {
                scrollTo(0, topChildHeight);
            }
        }
    }

    public interface OnPageChangeListener {
        void OnPageChange(int currentPage);
    }

}
