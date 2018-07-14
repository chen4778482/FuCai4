package com.example.fucai4

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.interresult_act.*

/**
 * Created by 42224 on 2018/5/14.
 */
class InterResultActivity : Activity() {
    //剪切板管理工具类
    private var mClipboardManager: ClipboardManager? = null
    var ListExtra: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.interresult_act)
        intresult_title.setTitleTextView("精选号")
        ListExtra = intent.getStringArrayListExtra("list_data")
        intresult_title.setOnImageButClickListener { v: View? ->
            this@InterResultActivity.finish()
        }
        mClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
        interresul_gv_jihe.setAdapter(GridViewSim(ListExtra, this@InterResultActivity, 2))

        interresul_tv_fuzhi.setOnClickListener { v: View? ->
            var op = ListExtra.toString().replace("[", "")
            op = op.replace("]", "")
            op = op.replace(",", "")
            op = op.replace("（来自组选王", "")
            op = op.replace("微信:aawwss1788)", "")
            mClipboardManager!!.setPrimaryClip(ClipData.newPlainText(null, op.toString() + "（来自组选王  微信:aawwss1788)"))
            var tos = Toast.makeText(this@InterResultActivity, "复制成功了精选号的全部号码", Toast.LENGTH_SHORT)
            tos.setGravity(Gravity.CENTER, 0, 0)
            tos.show()
        }
    }

    fun swap(list: ArrayList<String>, index1: Int, index2: Int): ArrayList<String> {
        val maxIndex = list.size - 1
        val minIndex = 0
        if (index1 < minIndex || index1 > maxIndex) throw IndexOutOfBoundsException()
        if (index2 < minIndex || index2 > maxIndex) throw IndexOutOfBoundsException()
        val tmp = list[index1]
        list[index1] = list[index2]
        list[index2] = tmp
        return list
    }

    fun bubbleSort(list: ArrayList<String>): ArrayList<String> {
        var sss: ArrayList<String>? = null
        if (list.size == 0) return sss!!
        val maxIndex = list.size - 1
        for (n in 0 until maxIndex) {
            for (i in 0 until maxIndex - n) {
                if (list[i] > list[i + 1]) {
                    sss = swap(list, i, i + 1)
                }
            }
        }
        return sss!!
    }
}