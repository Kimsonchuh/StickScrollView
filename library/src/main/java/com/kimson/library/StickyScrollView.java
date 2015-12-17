package com.kimson.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by zhujianheng on 12/16/15.
 */
public class StickyScrollView extends ScrollView {
    public static final String TAG = StickyScrollView.class.getSimpleName();
    public static final int PAGE_TOP = 0;
    public static final int PAGE_BOTTOM = 1;
    public static final double PERCENT = 0.4;

    private ViewGroup mChildLayout;
    private View mTopChildView;
    private View mBottomChildView;

    private Context mContext;
    private OnPageChangeListener listener;

    private Scroller mScroller;             //滑动类
    private int screenHeight;               //屏幕高度
    private int offsetDistance;             //topview的高度与屏幕的高度差
    private int topChildHeight;             //topview的高度
    private boolean isTouch;                //用户是否在触控屏幕
    private int currentPage;                //值为0时屏幕显示topview，值为1时屏幕显示bottomview
    private long downTime;
    private long upTime;
    private int upY;


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

    public void setListener(OnPageChangeListener listener) {
        this.listener = listener;
    }

    private void init(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mChildLayout = (ViewGroup) getChildAt(0);
        mTopChildView = mChildLayout.getChildAt(0);
        mBottomChildView = mChildLayout.getChildAt(1);
        topChildHeight = mTopChildView.getMeasuredHeight();
        screenHeight = getMeasuredHeight();
        offsetDistance = topChildHeight - screenHeight;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                downTime = System.currentTimeMillis();
                if (mScroller != null) {
                    mScroller.forceFinished(true);
                    mScroller = null;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                upY = getScrollY();
                upTime = System.currentTimeMillis();
                long duration = upTime - downTime;
                if (currentPage == PAGE_TOP) {
                    //下面的判断已经能确定用户是否往上滑
                    if (getScrollY() > offsetDistance) {
                        mScroller = new Scroller(mContext);
                        if (getScrollY() < (screenHeight * PERCENT + offsetDistance)) {
                            scrollToTarget(PAGE_TOP);
                        } else {
                            //切换到下界面
                            scrollToTarget(PAGE_BOTTOM);
                            currentPage = PAGE_BOTTOM;
                            return false;
                        }
                    }
                } else {
                    if (getScrollY() < topChildHeight) {
                        mScroller = new Scroller(mContext);
                        if (getScrollY() < (topChildHeight - screenHeight * PERCENT)) {
                            //切换到上界面
                            scrollToTarget(PAGE_TOP);
                            currentPage = PAGE_TOP;
                            return false;
                        } else {
                            scrollToTarget(PAGE_BOTTOM);
                        }
                    }
                }

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

//    private void scrollToDistance(final int distance) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //使用该方法时主要要使用新线程
//                scrollTo(0, distance);
//            }
//        }, 50);
//    }

    /**
     * 滚动到指定位置
     */
    private void scrollToTarget(int currentPage) {
        int delta;
        if (currentPage == PAGE_TOP) {
            delta = getScrollY() - offsetDistance;
            mScroller.startScroll(0, getScrollY(), 0, -delta, Math.abs(delta) / 3);
        } else if (currentPage == PAGE_BOTTOM) {
            delta = getScrollY() - topChildHeight;
            mScroller.startScroll(0, getScrollY(), 0, -delta, Math.abs(delta) / 3);
        }
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        // 调用startScroll的时候scroller.computeScrollOffset()返回true
        super.computeScroll();
        if (mScroller == null) {
            return;
        }
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
            if (mScroller.isFinished()) {
                mScroller = null;
//                listener.OnPageChange(currentPage);
            }
        }
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //滚动时的监听,当用户触屏滑动时不监听，t == getScrollY
        if (currentPage == PAGE_TOP) {
            if (getScrollY() > offsetDistance && !isTouch) {
                if (mScroller == null) {
                    scrollTo(0, offsetDistance);
                } else {
                    scrollToTarget(PAGE_TOP);
                }
            }
        } else if (currentPage == PAGE_BOTTOM) {
            if (getScrollY() < topChildHeight && !isTouch) {
                if (mScroller == null) {
                    scrollTo(0, topChildHeight);
                } else {
                    scrollToTarget(PAGE_BOTTOM);
                }
            }
        }
    }

    /**
     * 切换页面完成后的回调
     */
    public interface OnPageChangeListener {
        void OnPageChange(int currentPage);
    }

}
