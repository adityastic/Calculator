package com.aditya.calculator.datas;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.aditya.calculator.R;

import java.util.ArrayList;

public class ColorText {
    final String OP_PLUS = "+";
    final String OP_SUB = "-";
    final String OP_MUL = "x";
    final String OP_DIV = "/";

    Context context;
    String lastNumber, lastOperator;
    ArrayList<String> allList;
    double number1;
    boolean eq = false;

    public ColorText(Context context) {
        this.context = context;
        allList = new ArrayList<>();
        this.lastNumber = "";
        this.number1 = 0;
        this.lastOperator = OP_PLUS;
    }

    public double getAnswer() {
        return number1;
    }

    public String AccentFont(String str) {
        String accent = "#" + Integer.toHexString(ContextCompat.getColor(context, R.color.colorAccent)).substring(2);
        return "<font color=" + accent + ">" + str + "</font>";
    }

    public void add(String s) {
        if (!lastNumber.trim().equals("") && !lastNumber.endsWith(".")) {
            allList.add(lastNumber);

            if (!eq)
                equals();
            eq = false;

            switch (s) {
                case "/":
                    allList.add(OP_DIV);
                    lastOperator = OP_DIV;
                    break;

                case "X":
                    allList.add(OP_MUL);
                    lastOperator = OP_MUL;
                    break;

                case "+":
                    allList.add(OP_PLUS);
                    lastOperator = OP_PLUS;
                    break;

                case "-":
                    allList.add(OP_SUB);
                    lastOperator = OP_SUB;
                    break;
            }
            lastNumber = "";
        }
    }

    public void dot() {
        if (!lastNumber.contains(".") && lastNumber.length() < 6 && !eq) {
            if (lastNumber.length() < 1)
                lastNumber = lastNumber + "0.";
            else
                lastNumber = lastNumber + ".";
        }
    }

    public void equals() {
        if (!lastNumber.trim().equals("") && !lastNumber.endsWith(".") && !eq) {
            switch (lastOperator) {
                case OP_PLUS:
                    number1 = number1 + Double.parseDouble(lastNumber);
                    break;
                case OP_SUB:
                    number1 = number1 - Double.parseDouble(lastNumber);
                    break;
                case OP_MUL:
                    number1 = number1 * Double.parseDouble(lastNumber);
                    break;
                case OP_DIV:
                    number1 = number1 / Double.parseDouble(lastNumber);
                    break;
            }
            eq = true;
        }
    }

    public void addNumber(String s) {
        if (!eq && lastNumber.length() < 7)
            lastNumber = lastNumber + s;
    }

    public void clear() {
        if(!eq && lastNumber.length() > 0)
            lastNumber = lastNumber.substring(0,lastNumber.length()-1);
    }

    public void allclear() {
        allList.clear();
        lastNumber = "";
        eq = false;
        number1 = 0;
        lastOperator = OP_PLUS;
    }

    @Override
    public String toString() {
        String str = "";
        for (String temp : allList) {
            if (temp.equals(OP_DIV) || temp.equals(OP_PLUS) || temp.equals(OP_MUL) || temp.equals(OP_SUB))
                str = str + " " + AccentFont(temp) + " ";
            else
                str = str + temp;
        }
        return str + lastNumber;
    }
}
