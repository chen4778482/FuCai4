package com.example.fucai4.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.EditText


/**
 * Created by 42224 on 2018/4/19.
 */
class CustomEdit(context: Context, attrs: AttributeSet) : EditText(context, attrs) {

    private var oldText = ""

     override fun onDraw(canvas: Canvas) {
        var text = text.toString()
        text = text.replace("-", "")
        if (oldText != text) {
            oldText = text
            val builder = StringBuilder()
            for (i in 0 until text.length) {
                builder.append(text[i]).append("-")
            }
            setText(builder)
        }
        super.onDraw(canvas)
    }

}