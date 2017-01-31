package com.example.vova.flickrvt.common.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vova.flickrvt.R;
import com.example.vova.flickrvt.common.utils.DisplayUtils;

/**
 * Created by vova on 31.01.17.
 */

public class FreedomImageView extends ImageView {

    private float mWidth = 1;
    private float mHeight = 0.666F;
    private boolean mCoverMode = false;
    private boolean mSquareMode = false;

    private String mTextPosition;
    private static final String POSITION_NONE = "none";
    private static final String POSITION_TOP = "top";
    private static final String POSITION_BOTTOM = "bottom";

    public FreedomImageView(Context context) {
        super(context);
    }

    public FreedomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initialize(context, attrs, 0, 0);
    }

    public FreedomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initialize(context, attrs, defStyleAttr, 0);
    }

    private void initialize(Context c, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.FreedomImageView, defStyleAttr, defStyleRes);

        this.mCoverMode = a.getBoolean(R.styleable.FreedomImageView_fiv_cover_mode, false);
        this.mSquareMode = a.getBoolean(R.styleable.FreedomImageView_fiv_square, false);

        this.mTextPosition = a.getString(R.styleable.FreedomImageView_fiv_text_position);
        if (TextUtils.isEmpty(mTextPosition)
                || (!mTextPosition.equals(POSITION_TOP)
                && !mTextPosition.equals(POSITION_BOTTOM)
                && !mTextPosition.equals(POSITION_NONE))) {
            mTextPosition = POSITION_NONE;
        }

        a.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int[] size = getMeasureSize(MeasureSpec.getSize(widthMeasureSpec));
        setMeasuredDimension(size[0], size[1]);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setSize(int w, int h) {
        mWidth = w;
        mHeight = h;

        if (getMeasuredWidth() != 0) {
            int[] size = getMeasureSize(getMeasuredWidth());

            ViewGroup.LayoutParams params = getLayoutParams();
            params.width = size[0];
            params.height = size[1];
            setLayoutParams(params);
        }
    }

    private int[] getMeasureSize(int measureWidth) {
        if (mSquareMode) {
            return new int[] {measureWidth, measureWidth};
        }

        if (mCoverMode) {
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            int screenHeight = getResources().getDisplayMetrics().heightPixels;
            float limitHeight = screenHeight - new DisplayUtils(getContext()).dpToPx(300);

            if (1.0 * mHeight / mWidth * screenWidth <= limitHeight) {
                return new int[] {
                        (int) (limitHeight * mWidth / mHeight),
                        (int) limitHeight};
            }
        }

        return new int[] {
                measureWidth,
                (int) (measureWidth * mHeight / mWidth)};
    }
}