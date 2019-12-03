package com.aditya.calculator.application

import android.app.Application
import android.graphics.Typeface
import java.lang.reflect.Field

class ApplicationActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            val customFontTypeface = Typeface.createFromAsset(applicationContext.assets, "GoogleSans-Regular.ttf")
            val defaultFontTypefaceField: Field = Typeface::class.java.getDeclaredField("SERIF")
            defaultFontTypefaceField.isAccessible = true
            defaultFontTypefaceField[null] = customFontTypeface
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
}