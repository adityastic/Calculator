package com.aditya.calculator.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import kotlin.math.min

class SquareButton : AppCompatButton {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measuredWidth = View.getDefaultSize(suggestedMinimumWidth,
                widthMeasureSpec)
        val measuredHeight = View.getDefaultSize(suggestedMinimumHeight,
                heightMeasureSpec)
        // Ensure this view is always square.
        val min = min(measuredHeight, measuredWidth)
        setMeasuredDimension(min, min)
    }
}