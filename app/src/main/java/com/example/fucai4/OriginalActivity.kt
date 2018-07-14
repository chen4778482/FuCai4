package com.example.fucai4

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.fucai4.view.TitleView
import kotlinx.android.synthetic.main.act_origina.*

/**
 * Created by 42224 on 2018/4/20.
 */
class OriginalActivity : Activity() {
    var a6 = ArrayList<String>()//原始数据 组6
    var b3 = ArrayList<String>()//原始数据 组3
    var s1: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_origina)
        orig_title.setOnImageButClickListener(TitleView.OnImageButClickListener { v: View? ->
            finish()
        })
        orig_title.setTitleTextView("原始值")

        b3 = intent.getStringArrayListExtra("b3")
        a6 = intent.getStringArrayListExtra("a6")
        s1 = intent.getStringExtra("s1")
        Log.e("1111111", s1)
        tv_y.setText(s1.toString())
        orig_gv_z6.adapter = GridViewSim(a6, this@OriginalActivity, 2)
        orig_gv_z3.adapter = GridViewSim(b3, this@OriginalActivity, 2)
    }

}