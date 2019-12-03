package com.aditya.calculator.data

enum class Operator(val symbol: String) {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("x"),
    DIVIDE("/");

    companion object{
        fun operatorOf(value: String): Operator? = values().find { it.symbol == value }
    }
}