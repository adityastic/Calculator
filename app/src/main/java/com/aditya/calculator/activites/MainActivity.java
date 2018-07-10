package com.aditya.calculator.activites;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aditya.calculator.R;
import com.aditya.calculator.datas.ColorText;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bdot, beq, bc, bac, badd, bmul, bsub, bdiv;
    TextView upperText, lowerText;

    ColorText text;

    public void setLowerText(double number) {

        int num = (int) number;
        double dec = number - num;

        String decimal, integer;

        integer = String.valueOf(num);
        decimal = String.format("%.2f", dec).substring((dec>0)?2:3);

        int textSize1 = getResources().getDimensionPixelSize(R.dimen.text_size_1);
        int textSize2 = getResources().getDimensionPixelSize(R.dimen.text_size_2);
        decimal = "." + decimal;
        SpannableString span1 = new SpannableString(integer);
        span1.setSpan(new AbsoluteSizeSpan(textSize1), 0, integer.length(), SPAN_INCLUSIVE_INCLUSIVE);
        SpannableString span2 = new SpannableString(decimal);
        span2.setSpan(new AbsoluteSizeSpan(textSize2), 0, decimal.length(), SPAN_INCLUSIVE_INCLUSIVE);
        CharSequence finalText = TextUtils.concat(span1, span2);
        lowerText.setText(finalText);
    }

    public void setUpperText(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            upperText.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
        } else {
            upperText.setText(Html.fromHtml(text));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b0 = findViewById(R.id.button0);
        bdot = findViewById(R.id.buttondot);
        beq = findViewById(R.id.buttonEq);
        bc = findViewById(R.id.buttonC);
        bac = findViewById(R.id.buttonAC);
        badd = findViewById(R.id.buttonAdd);
        bmul = findViewById(R.id.buttonMul);
        bsub = findViewById(R.id.buttonSub);
        bdiv = findViewById(R.id.buttonDiv);

        upperText = findViewById(R.id.operate);
        lowerText = findViewById(R.id.ans);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        bdot.setOnClickListener(this);
        beq.setOnClickListener(this);
        bc.setOnClickListener(this);
        bac.setOnClickListener(this);
        badd.setOnClickListener(this);
        bmul.setOnClickListener(this);
        bsub.setOnClickListener(this);
        bdiv.setOnClickListener(this);

        //String text = "50 " + AccentFont("x") + " 60 " + AccentFont("-") + " 20";

        text = new ColorText(this);

        setUpperText("0");
        setLowerText(0);

    }

    @Override
    public void onClick(View v) {
        Button b = findViewById(v.getId());
        switch (v.getId()) {
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
            case R.id.button0:
                text.addNumber(b.getText().toString());
                setUpperText(text.toString());
                break;
            case R.id.buttonAC:
                setUpperText("0");
                text.allclear();
                setLowerText(0);
                break;

            case R.id.buttonC:
                text.clear();
                setUpperText(text.toString());
                break;

            case R.id.buttondot:
                text.dot();
                setUpperText(text.toString());
                break;

            case R.id.buttonAdd:
            case R.id.buttonSub:
            case R.id.buttonMul:
            case R.id.buttonDiv:
                text.add(b.getText().toString());
                setUpperText(text.toString());
                setLowerText(text.getAnswer());
                break;

            case R.id.buttonEq:
                text.equals();
                setUpperText(text.toString());
                setLowerText(text.getAnswer());
                break;

        }
    }
}
