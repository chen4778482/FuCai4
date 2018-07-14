package com.example.fucai4

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.fucai4.utlis.GongYong_Zuhao
import com.example.fucai4.view.TitleView
import kotlinx.android.synthetic.main.act_resu2.*
import android.view.Gravity
import android.widget.TextView


/**
 * Created by 42224 on 2018/4/20.
 */
class ResultActivity2 : Activity() {

    var a: MutableList<String> = ArrayList()//原始值 组6
    var b: MutableList<String> = ArrayList()//原始值 组3
    //剪切板管理工具类
    private var mClipboardManager: ClipboardManager? = null

    var t3 = ArrayList<String>()
    var t6 = ArrayList<String>()

    var a6 = ArrayList<String>()//原始数据 组6
    var b3 = ArrayList<String>()//原始数据 组3

    var string_array6: Array<String>? = null
    var string_array2: Array<String>? = null
    var shang: String = ""
    var shang1: String = ""
    var shang2: String = ""
    var ti_zhongyao_et: String = ""
    var t: Int = 0
    var mian_et_can1: String? = null
    var mian_et_can2: String? = null
    var mian_et_can3: String? = null
    var shuoming: String? = null

    var s1: String? = null

    var tpye: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_resu2)
        res_title.setOnImageButClickListener(TitleView.OnImageButClickListener { v: View? ->
            finish()
        })
        mClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?

        tpye = intent.getStringExtra("type")
        when (tpye) {

            "1" -> {//体彩计算方法
                t3 = intent.getStringArrayListExtra("t3")
                t6 = intent.getStringArrayListExtra("t6")
                b3 = intent.getStringArrayListExtra("b3")
                a6 = intent.getStringArrayListExtra("a6")
                s1 = intent.getStringExtra("s1")
                val c = this.intent.extras
                string_array6 = c!!.getStringArray("string_array6")
                string_array2 = c!!.getStringArray("string_array2")
                shang = intent.getStringExtra("shang")
                shuoming = intent.getStringExtra("shuoming")
                ti_zhongyao_et = intent.getStringExtra("ti_zhongyao_et")
                resul_tv_shuo.visibility = View.GONE
                resul_et_shang.setText(shang)
            }
        }

        resul_gv_z6.adapter = GridViewSim(t6, this@ResultActivity2, 1)
        resul_gv_z3.adapter = GridViewSim(t3, this@ResultActivity2, 1)
        a = t6
        b = t3
        tv_z6.setOnClickListener { v: View? ->
            var op = "组6号码：" + a.toString() + "\n组3号码：" + b.toString()
            op.replace("[", "")
            op = op.replace("]", "")
            op = op.replace(",", "")

            mClipboardManager!!.primaryClip = ClipData.newPlainText(null, op.toString() + "（来自组选王  微信:aawwss1788)")
            var tos = Toast.makeText(this@ResultActivity2, "复制了全部号码", Toast.LENGTH_SHORT)
            tos.setGravity(Gravity.CENTER, 0, 0)
            tos.show()
        }

        resul_suan.setOnClickListener { v: View? ->

            shang1 = resul_et_shang.text.toString()
            shang2 = resul_et_shang2.text.toString()
            if (shang2.length != 3) {
                var tos = Toast.makeText(this@ResultActivity2, "参考号码必须是三位数，不然无法显示", Toast.LENGTH_SHORT)
                tos.setGravity(Gravity.CENTER, 0, 0)
                tos.show()
                return@setOnClickListener
            }
            if (shang1.length == 3) {
                a.clear()
                b.clear()
                t3.clear()
                t6.clear()
                a6.clear()
                b3.clear()
                when (tpye) {
                    "0" -> {
                        var lin_a6 = GongYong_Zuhao.zu6(mian_et_can1?.toString(), mian_et_can2?.toString(), mian_et_can3?.toString())
                        lin_a6 = bubbleSort(lin_a6)
                        var s = GongYong_Zuhao.CalcBama(shang, 0, true)
                        s1 = s[0].toString()
                        var lin_b3 = GongYong_Zuhao.Zuhao3New1(s1)
                        var shuoming: String = lin_b3.get(1).toString()

                        var condition = lin_b3[2].toString()
                        condition = condition.replace("[", "")
                        condition = condition.replace("]", "")
                        var shuominglist = shuoming!!.split(" ")
                        shuoming = shuominglist.get(1).replace("]", "") + "\n\n" + shuominglist.get(0).replace("[", "")
                        resul_tv_shuo.setText(shuoming)

//                        Log.e("condition--------------", condition)
//                        for (i in lin_a6.indices) {
//                            if (!GongYong_Zuhao.test(lin_a6[i], condition, 5)) a6.add(lin_a6[i])
//                        }
//                        Log.e("新组6数据", a6.toString())
                        Log.e("新组6数据长度", "" + a6.size)
                        a = lin_a6
                        for (i in string_array6!!.indices) {
                            var value = string_array6!![i]
                            if (i == 0) { //胆码执行
                                var danma_text = value.replace("-", "")
                                if (danma_text != "") {
                                    for (item in a) {
                                        if (GongYong_Zuhao.test(item, danma_text, 1)) {
                                            t6.add(item)
                                        }
                                    }
                                    a = t6
                                }
                            }
                            if (i == 1) { //两码执行
                                var erma_text = value//.split("-", "")
                                Log.e("输入的两码值：", erma_text)
                                if (erma_text != "") {
                                    for (i in a.indices) {
                                        if (GongYong_Zuhao.test(a[i], erma_text, 2)) t6.add(a[i])
                                    }
                                    a = t6

                                }
                            }
                            if (i == 2) { //复式执行
                                var kua_text = value//.replace("-", "")
                                Log.e("输入的复式：", kua_text)
                                if (kua_text != "") {
                                    for (i in a.indices) {
                                        if (GongYong_Zuhao.test(a[i], kua_text, 5)) t6.add(a[i])
                                    }
                                    a = t6
                                }
                            }
                            if (i == 3) { //断组执行
                                var kua_text = value
                                // Log.e("输入的断组：", kua_text)
                                if (!kua_text.equals("")) {
                                    for (i in a.indices) {
                                        if (!GongYong_Zuhao.test(a[i], kua_text, 6)) t6.add(a[i])
                                    }
                                    a = t6
                                }
                            }
                            if (i == 4) { //胆组执行
                                if (value != "") {
                                    var danzu_text = value.split("-")
                                    if (danzu_text.size > 1 && danzu_text.size <= 3) {
                                        var newd1: MutableList<String> = ArrayList()
                                        var newd2: MutableList<String> = ArrayList()
                                        var newd3: MutableList<String> = ArrayList()
                                        var d1: MutableList<String> = ArrayList()
                                        if (!danzu_text.equals("")) {
                                            for (item in a) {
                                                for (x in danzu_text!!.indices) {
                                                    if (GongYong_Zuhao.test(item, danzu_text[x], 1)) {
                                                        if (x == 0) newd1.add(item)
                                                        if (x == 1) newd2.add(item)
                                                        if (x == 2) newd3.add(item)
                                                        if (danzu_text.size == 1) t6.add(item)
                                                    }
                                                }
                                            }
                                            for (n1 in newd1) {
                                                for (n2 in newd2) {
                                                    if (n2 == n1) {
                                                        d1.add(n2)
                                                    }
                                                }
                                            }
                                            for (n in d1) {
                                                if (newd3.size != 0) {
                                                    for (n3 in newd3) {
                                                        if (n3 == n) {
                                                            t6.add(n3)
                                                        }
                                                    }
                                                } else {
                                                    t6.add(n)
                                                }
                                            }
                                            a = t6
                                        }
                                    } else {
                                        Toast.makeText(this@ResultActivity2, "最少输入2组胆组,最多输入3组胆组", Toast.LENGTH_SHORT).show()
                                        return@setOnClickListener
                                    }

                                }
                            }
                            if (i == 5) {//杀码
                                var danma_text = value.replace("-", "")
                                if (danma_text != "") {
                                    for (item in a) {
                                        if (GongYong_Zuhao.test(item, danma_text, 7)) {
                                            t6.add(item)
                                        }
                                    }
                                    a = t6
                                }
                            }
                            t6 = ArrayList()
                        }


                        b3 = lin_b3[0] as ArrayList<String>
                        b = b3

                        for (i in string_array2!!.indices) {
                            var value = string_array2!![i]
                            if (i == 0) { //胆码执行
                                var danma_text = value.replace("-", "")
                                if (danma_text != "") {
                                    for (item in b) {
                                        if (GongYong_Zuhao.test(item, danma_text, 1)) {
                                            t3.add(item)
                                        }
                                    }
                                    b = t3
                                }
                            }
                            if (i == 1) { //断组执行
                                // Log.e("输入的断组：", kua_text)
                                if (value != "") {
                                    for (i in b.indices) {
                                        if (!GongYong_Zuhao.test(b[i], value, 6)) t3.add(b[i])
                                    }
                                    b = t3
                                }
                            }
                            if (i == 2) {
                                //杀组合
                                var shazuhe_text = value//.replace("-", "")
                                Log.e("输入的组合：", shazuhe_text)
                                if (shazuhe_text != "") {
                                    var s = shazuhe_text.split("-")
                                    for (item in a) {
                                        for (it in s) {
                                            if (!GongYong_Zuhao.xiangtong1(it, item)) {
                                                t3.add(item)
                                            }
                                        }
                                    }
                                    b = GongYong_Zuhao.unique(t3)
                                }
                            }

                            t3 = ArrayList()
                        }
                        resul_gv_z6.setAdapter(GridViewSim(a as java.util.ArrayList<String>?, this@ResultActivity2, 2))
                        resul_gv_z3.setAdapter(GridViewSim(b as java.util.ArrayList<String>?, this@ResultActivity2, 2))

                        t = 1
                    }
                    "1" -> {//体彩计算方法

                        var s = GongYong_Zuhao.CalcBama(shang, 0, false)
                        s1 = s[0].toString()
                        var tiaojian = GongYong_Zuhao.zhongyaocanshu(ti_zhongyao_et)
                        var zu6 = GongYong_Zuhao.xinzu6(s[0], tiaojian, s[1])
                        a6 = bubbleSort(zu6)
                        a = a6

                        for (i in string_array6!!.indices) {
                            var value = string_array6!![i]
                            if (i == 0) { //胆码执行
                                var danma_text = value.replace("-", "")
                                if (danma_text != "") {
                                    Log.e("输入的胆码值：", danma_text)
                                    for (item in a) {
                                        if (GongYong_Zuhao.test(item, danma_text, 1)) {
                                            t6.add(item)
                                        }
                                    }
                                    a = t6
                                }
                            }
                            if (i == 1) { //两码执行
                                var erma_text = value//.split("-", "")
                                Log.e("输入的两码值：", erma_text)
                                if (erma_text != "") {
                                    for (i in a.indices) {
                                        if (GongYong_Zuhao.test(a[i], erma_text, 2)) t6.add(a[i])
                                    }
                                    a = t6
                                }
                            }
                            if (i == 2) { //和尾值执行
                                var he_text = value.replace("-", "")
                                Log.e("输入的和尾值：", he_text)
                                if (he_text != "") {
                                    for (i in a.indices) {
                                        if (GongYong_Zuhao.test(a[i], he_text, 3)) t6.add(a[i])
                                    }
                                    a = t6
                                }
                            }
                            if (i == 3) { //跨度执行
                                var kua_text = value.replace("-", "")
                                Log.e("输入的跨度：", kua_text)
                                if (!kua_text.equals("")) {
                                    for (i in a.indices) {
                                        if (GongYong_Zuhao.test(a[i], kua_text, 4)) t6.add(a[i])
                                    }
                                    a = t6
                                }
                            }
                            if (i == 4) { //复式执行
                                var kua_text = value//.replace("-", "")
                                Log.e("输入的复式：", kua_text)
                                if (kua_text != "") {
                                    for (i in a.indices) {
                                        if (GongYong_Zuhao.test(a[i], kua_text, 5)) t6.add(a[i])
                                    }
                                    a = t6
                                }
                            }
                            if (i == 5) { //断组执行
                                var kua_text = value
                                Log.e("输入的断组：", kua_text)
                                if (kua_text != "") {
                                    for (i in a.indices) {
                                        if (!GongYong_Zuhao.test(a[i], kua_text, 6)) t6.add(a[i])
                                    }
                                    a = t6
                                }
                            }
                            if (i == 6) { //胆组执行
                                if (value != "") {
                                    Log.e("输入的跨度：", value)
                                    var danzu_text = value.split("-")
                                    if (danzu_text.size in 2..3) {
                                        var newd1: MutableList<String> = ArrayList()
                                        var newd2: MutableList<String> = ArrayList()
                                        var newd3: MutableList<String> = ArrayList()
                                        var d1: MutableList<String> = ArrayList()
                                        if (!danzu_text.equals("")) {
                                            for (item in a) {
                                                for (x in danzu_text!!.indices) {
                                                    if (GongYong_Zuhao.test(item, danzu_text[x], 1)) {
                                                        if (x == 0) newd1.add(item)
                                                        if (x == 1) newd2.add(item)
                                                        if (x == 2) newd3.add(item)
                                                        if (danzu_text.size == 1) t6.add(item)
                                                    }
                                                }
                                            }
                                            for (n1 in newd1) {
                                                for (n2 in newd2) {
                                                    if (n2 == n1) {
                                                        d1.add(n2)
                                                    }
                                                }
                                            }
                                            for (n in d1) {
                                                if (newd3.size != 0) {
                                                    for (n3 in newd3) {
                                                        if (n3 == n) {
                                                            t6.add(n3)
                                                        }
                                                    }
                                                } else {
                                                    t6.add(n)
                                                }
                                            }
                                            a = t6
                                        }
                                    } else {
                                        Toast.makeText(this@ResultActivity2, "最少输入2组胆组,最多输入3组胆组", Toast.LENGTH_SHORT).show()
                                        return@setOnClickListener
                                    }
                                }
                            }
                            if (i == 7) {//杀码
                                var danma_text = value.replace("-", "")
                                Log.e("输入的杀码：", danma_text)
                                if (danma_text != "") {
                                    for (item in a) {
                                        if (GongYong_Zuhao.test(item, danma_text, 7)) {
                                            t6.add(item)
                                        }
                                    }
                                    a = t6
                                }
                            }
                            if (i == 8) { //杀组合
                                var shazuhe_text = value//.replace("-", "")
                                Log.e("输入的杀码：", shazuhe_text)
                                if (shazuhe_text != "") {
                                    var s = shazuhe_text.split("-")
                                    for (item in a) {
                                        for (it in s) {
                                            if (!GongYong_Zuhao.xiangtong1(it, item)) {
                                                t6.add(item)
                                            }
                                        }
                                    }
                                    a = GongYong_Zuhao.unique(t6)
                                }
                            }
                            t6 = ArrayList()
                        }

                        var lin_b3 = GongYong_Zuhao.Zuhao3New1(s1)
                        b3 = lin_b3[0] as ArrayList<String>
                        b = b3

                        for (i in string_array2!!.indices) {
                            var value = string_array2!![i]
                            if (i == 0) { //胆码执行
                                var danma_text = value.replace("-", "")
                                if (danma_text != "") {
                                    for (item in b) {
                                        if (GongYong_Zuhao.zu3(item, danma_text, tiaojian)) {
                                            t3.add(item)
                                        }
                                    }
                                    b = t3
                                }
                            }
                            if (i == 1) {
                                //杀组合
                                var shazuhe_text = value//.replace("-", "")
                                Log.e("输入的组合：", shazuhe_text)
                                if (shazuhe_text != "") {
                                    var s = shazuhe_text.split("-")
                                    for (item in a) {
                                        for (it in s) {
                                            if (!GongYong_Zuhao.xiangtong1(it, item)) {
                                                t3.add(item)
                                            }
                                        }
                                    }
                                    b = GongYong_Zuhao.unique(t3)
                                }
                            }
                            t3 = ArrayList()
//
//                                mAdapter3?.refresh(b)
//                                fuzhi_msg?.text = lin_b3[2].toString()

                        }

                        if (a!!.size != 0) {
                            var data: ArrayList<String> = GongYong_Zuhao.unique(a)
                            a = bubbleSort(data)
                        }
                        resul_gv_z6.setAdapter(GridViewSim(a as java.util.ArrayList<String>?, this@ResultActivity2, 2))
                        resul_gv_z3.setAdapter(GridViewSim(b as java.util.ArrayList<String>?, this@ResultActivity2, 2))
                        var condition1 = lin_b3[1].toString()
                        condition1 = condition1.replace("[", "")
                        condition1 = condition1.replace("]", "")
                        resul_tv_shuoming2.setText(condition1)
                        setTextColor(resul_tv_shuoming2)
                    }
                }
            }
        }
        resul_yuan.setOnClickListener { v: View? ->
            var intent = Intent(this@ResultActivity2, OriginalActivity::class.java)
            intent.putStringArrayListExtra("a6", a6 as java.util.ArrayList<String>?)
            intent.putStringArrayListExtra("b3", b3 as java.util.ArrayList<String>?)
            intent.putExtra("s1", s1)
            startActivity(intent)
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
//        Log.e("11111111111", list.toString())
//        Log.e("22222222222", "" + list.size)
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

    fun setTextColor(textView: TextView) {

        val style = SpannableStringBuilder(textView.text.toString())
        style.setSpan(ForegroundColorSpan(Color.RED), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        style.setSpan(ForegroundColorSpan(Color.RED), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        style.setSpan(ForegroundColorSpan(Color.RED), 9, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        style.setSpan(ForegroundColorSpan(Color.RED), 19, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        style.setSpan(ForegroundColorSpan(Color.RED), 21, 22, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        style.setSpan(ForegroundColorSpan(Color.RED), 25, 26, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        style.setSpan(ForegroundColorSpan(Color.RED), 27, 28, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = style


    }
}



