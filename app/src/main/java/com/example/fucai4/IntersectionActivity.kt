package com.example.fucai4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.fucai4.utlis.Contf
import com.example.fucai4.utlis.PreferencesUtil
import kotlinx.android.synthetic.main.inter_act.*


/**
 *
 * Created by 42224 on 2018/5/14.
 */
class IntersectionActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inter_act)

        inter_title.setTitleTextView("求交集")
        inter_title.setOnImageButClickListener { v: View? ->
            setPreferencesUtil(Contf.inter_edssss1, inter_ed1.text.toString())
            setPreferencesUtil(Contf.inter_edssss2, inter_ed2.text.toString())
            this@IntersectionActivity.finish()
        }

        setview(Contf.inter_edssss1, inter_ed1)
        setview(Contf.inter_edssss2, inter_ed2)

        inter_but_jiao.setOnClickListener { v: View? ->
            var inter_eds1 = inter_ed1.text.toString()
            var inter_eds2 = inter_ed2.text.toString()
            if (TextUtils.isEmpty(inter_eds1)) {
                Toast.makeText(this@IntersectionActivity, "交集1不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(inter_eds2)) {
                Toast.makeText(this@IntersectionActivity, "交集2不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
//            Log.e("11111111111111", inter_eds2)
//            inter_eds2 = inter_eds2.substring(0, inter_eds2.indexOf("（"))
//            Log.e("2222222222222", inter_eds2)

            setPreferencesUtil(Contf.inter_edssss1, inter_eds1)
            setPreferencesUtil(Contf.inter_edssss2, inter_eds2)
            var list_ed1 = inter_eds1.split(" ")
            var list_ed2 = inter_eds2.split(" ")
            if (list_ed1.size == 1) {
                list_ed1 = inter_eds1.split("  ")
            }
            if (list_ed2.size == 1) {
                list_ed2 = inter_eds2.split("  ")
            }

            var list_data = list_ed1.intersect(list_ed2)
            var intent = Intent(this@IntersectionActivity, InterResultActivity::class.java)

            var list_data1: ArrayList<String>? = ArrayList(list_data)
            intent.putStringArrayListExtra("list_data", list_data1)
            startActivity(intent)
        }
        inter_but_kong.setOnClickListener { v: View? ->
            PreferencesUtil.remove(this@IntersectionActivity, Contf.inter_edssss1)
            PreferencesUtil.remove(this@IntersectionActivity, Contf.inter_edssss2)
            inter_ed1.setText("")
            inter_ed2.setText("")
            Toast.makeText(this@IntersectionActivity, "数据已经清空了", Toast.LENGTH_SHORT).show()
        }
    }

    fun setview(kay: String, editText: EditText) {
        var msg: String = PreferencesUtil.get(this@IntersectionActivity, kay, "").toString()
        if (!TextUtils.isEmpty(msg)) {
            editText.setText(msg)
        }
    }

    fun setPreferencesUtil(kay: String, vlaue: String) {
        PreferencesUtil.remove(this@IntersectionActivity, kay)
        PreferencesUtil.put(this@IntersectionActivity, kay, vlaue)
    }

}