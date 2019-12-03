package com.aditya.calculator.activites

import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.aditya.calculator.R
import com.aditya.calculator.data.ColorText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val text by lazy { ColorText(this) }

    private fun setLowerText(number: Double) {
        var decimal: String

        val textSize1 = resources.getDimensionPixelSize(R.dimen.text_size_1)
        val textSize2 = resources.getDimensionPixelSize(R.dimen.text_size_2)

        val dec = number - number.toInt()
        val integer: String = number.toInt().toString()

        decimal = String.format("%.2f", dec).substring(if (dec > 0) 2 else 3)
        decimal = ".$decimal"

        val span1 = SpannableString(integer)
        span1.setSpan(AbsoluteSizeSpan(textSize1), 0, integer.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        val span2 = SpannableString(decimal)
        span2.setSpan(AbsoluteSizeSpan(textSize2), 0, decimal.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        val finalText = TextUtils.concat(span1, span2)

        operate.text = finalText
    }

    private fun setUpperText(text: String?) {
        ans.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button0.setOnClickListener(this)
        buttondot.setOnClickListener(this)
        buttonEq.setOnClickListener(this)
        buttonC.setOnClickListener(this)
        buttonAC.setOnClickListener(this)
        buttonAdd.setOnClickListener(this)
        buttonMul.setOnClickListener(this)
        buttonSub.setOnClickListener(this)
        buttonDiv.setOnClickListener(this)

        setUpperText("0")
        setLowerText(0.0)
    }

    override fun onClick(v: View) {
        val b = findViewById<Button>(v.id)
        when (v.id) {
            R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button0 -> {
                text.addNumber(b.text.toString())
                setUpperText(text.toString())
            }
            R.id.buttonAC -> {
                setUpperText("0")
                text.allClear()
                setLowerText(0.0)
            }
            R.id.buttonC -> {
                text.clear()
                setUpperText(text.toString())
            }
            R.id.buttondot -> {
                text.dot()
                setUpperText(text.toString())
            }
            R.id.buttonAdd, R.id.buttonSub, R.id.buttonMul, R.id.buttonDiv -> {
                text.add(b.text.toString())
                setUpperText(text.toString())
                setLowerText(text.getAnswer())
            }
            R.id.buttonEq -> {
                text.equals()
                setUpperText(text.toString())
                setLowerText(text.getAnswer())
            }
        }
    }
}