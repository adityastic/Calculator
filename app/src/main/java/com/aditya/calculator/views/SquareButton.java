package com.aditya.calculator.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class SquareButton extends android.support.v7.widget.AppCompatButton {

    public SquareButton(Context context) {
        super(context);
    }

    public SquareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = getDefaultSize(getSuggestedMinimumWidth(),
                widthMeasureSpec);
        int measuredHeight = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        // Ensure this view is always square.
        int min = Math.min(measuredHeight, measuredWidth);
        setMeasuredDimension(min, min);
    }
}