package com.aditya.calculator.data

import android.content.Context
import androidx.core.content.ContextCompat
import com.aditya.calculator.R
import java.util.*

class ColorText(private var context: Context) {
    private var lastNumber: String
    private var lastOperator: Operator
    private var allList: ArrayList<String> = ArrayList()
    private var answer: Double
    private var eq = false

    fun getAnswer() = answer

    private fun generateAccentFont(str: String): String {
        val accent = "#" + Integer.toHexString(ContextCompat.getColor(context, R.color.colorAccent)).substring(2)
        return "<font color=$accent>$str</font>"
    }

    fun add(s: String?) {
        if (lastNumber.trim { it <= ' ' } != "" && !lastNumber.endsWith(".")) {
            allList.add(lastNumber)
            if (!eq) equals()
            eq = false

            s?.let {
                val currentOperator = Operator.operatorOf(it)
                if (currentOperator != null) {
                    allList.add(currentOperator.symbol)
                    lastOperator = currentOperator
                }
            }
            lastNumber = ""
        }
    }

    fun dot() {
        if (!lastNumber.contains(".") && lastNumber.length < 6 && !eq) {
            lastNumber = if (lastNumber.isEmpty()) lastNumber + "0." else "$lastNumber."
        }
    }

    fun equals() {
        if (lastNumber.trim { it <= ' ' } != "" && !lastNumber.endsWith(".") && !eq) {
            answer = when (lastOperator) {
                Operator.PLUS -> answer + lastNumber.toDouble()
                Operator.MINUS -> answer - lastNumber.toDouble()
                Operator.MULTIPLY -> answer * lastNumber.toDouble()
                Operator.DIVIDE -> answer / lastNumber.toDouble()
            }
            eq = true
        }
    }

    fun addNumber(s: String) {
        if (!eq && lastNumber.length < 7) lastNumber += s
    }

    fun clear() {
        if (!eq && lastNumber.isNotEmpty()) lastNumber = lastNumber.substring(0, lastNumber.length - 1)
    }

    fun allClear() {
        allList.clear()
        lastNumber = ""
        eq = false
        answer = 0.0
        lastOperator = Operator.PLUS
    }

    override fun toString(): String {
        var str = ""
        for (temp in allList) {
            str = if (Operator.operatorOf(temp) != null) str + " " + generateAccentFont(temp) + " " else str + temp
        }
        return str + lastNumber
    }

    init {
        lastNumber = ""
        answer = 0.0
        lastOperator = Operator.PLUS
    }
}