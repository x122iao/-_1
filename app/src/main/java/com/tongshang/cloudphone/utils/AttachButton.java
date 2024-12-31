package com.tongshang.cloudphone.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import androidx.annotation.Nullable;

import com.tongshang.cloudphone.R;


/**
 * 自定义View实现拖动并自动吸边效果
 * <p>
 * 处理滑动和贴边 {@link #onTouchEvent(MotionEvent)}
 * 处理事件分发 {@link #dispatchTouchEvent(MotionEvent)}
 * </p>
 *
 * @attr customIsAttach  //是否需要自动吸边
 * @attr customIsDrag    //是否可拖曳
 */
public class AttachButton extends View {
    private float mLastRawX;
    private float mLastRawY;
    private final String TAG = "AttachButton";
    private boolean isDrug = false;
    private int mRootMeasuredWidth = 0;
    private int mRootMeasuredHeight = 0;
    private int mRootTopY = 0;
    private boolean customIsAttach;
    private boolean customIsDrag;

    private Handler mHandler;
    private Runnable mFadeOutRunnable;

    public AttachButton(Context context) {
        this(context, null);
    }

    public AttachButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttachButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
        initAttrs(context, attrs);

        // 初始化 Handler 和 Runnable
        mHandler = new Handler(Looper.getMainLooper());
        mFadeOutRunnable = new Runnable() {
            @Override
            public void run() {
                // 设置透明度逐渐增加
                AttachButton.this.animate()
                        .alpha(0.5f) // 设置目标透明度（0.5 表示 50% 透明度）
                        .setDuration(500)
                        .start();
            }
        };
    }

    /**
     * 初始化自定义属性
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray mTypedAttay = context.obtainStyledAttributes(attrs, R.styleable.AttachButton);
        customIsAttach = mTypedAttay.getBoolean(R.styleable.AttachButton_customIsAttach, true);
        customIsDrag = mTypedAttay.getBoolean(R.styleable.AttachButton_customIsDrag, true);
        mTypedAttay.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (customIsDrag) {
            // 重置透明度，并取消任何未执行的透明度增加任务
            setAlpha(1.0f);
            mHandler.removeCallbacks(mFadeOutRunnable);

            // 开始新的无操作检测任务
            mHandler.postDelayed(mFadeOutRunnable, 2000); // 2秒后执行透明度增加

            float mRawX = ev.getRawX();
            float mRawY = ev.getRawY();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isDrug = false;
                    mLastRawX = mRawX;
                    mLastRawY = mRawY;
                    ViewGroup mViewGroup = (ViewGroup) getParent();
                    if (mViewGroup != null) {
                        int[] location = new int[2];
                        mViewGroup.getLocationInWindow(location);
                        mRootMeasuredHeight = mViewGroup.getMeasuredHeight();
                        mRootMeasuredWidth = mViewGroup.getMeasuredWidth();
                        mRootTopY = location[1];
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mRawX >= 0 && mRawX <= mRootMeasuredWidth && mRawY >= mRootTopY && mRawY <= (mRootMeasuredHeight + mRootTopY)) {
                        float differenceValueX = mRawX - mLastRawX;
                        float differenceValueY = mRawY - mLastRawY;
                        if (!isDrug) {
                            if (Math.sqrt(differenceValueX * differenceValueX + differenceValueY * differenceValueY) < 2) {
                                isDrug = false;
                            } else {
                                isDrug = true;
                            }
                        }
                        float ownX = getX();
                        float ownY = getY();
                        float endX = ownX + differenceValueX;
                        float endY = ownY + differenceValueY;
                        float maxX = mRootMeasuredWidth - getWidth();
                        float maxY = mRootMeasuredHeight - getHeight();
                        endX = endX < 0 ? 0 : endX > maxX ? maxX : endX;
                        endY = endY < 0 ? 0 : endY > maxY ? maxY : endY;
                        setX(endX);
                        setY(endY);
                        mLastRawX = mRawX;
                        mLastRawY = mRawY;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (customIsAttach) {
                        if (isDrug) {
                            float center = mRootMeasuredWidth / 2;
                            if (mLastRawX <= center) {
                                AttachButton.this.animate()
                                        .setInterpolator(new BounceInterpolator())
                                        .setDuration(500)
                                        .x(0)
                                        .start();
                            } else {
                                AttachButton.this.animate()
                                        .setInterpolator(new BounceInterpolator())
                                        .setDuration(500)
                                        .x(mRootMeasuredWidth - getWidth())
                                        .start();
                            }
                        }
                    }
                    break;
            }
        }
        return isDrug || super.onTouchEvent(ev);
    }
}