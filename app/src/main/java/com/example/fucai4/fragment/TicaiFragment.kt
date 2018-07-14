package com.example.fucai4.fragment

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager

import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.fucai4.R
import com.example.fucai4.ResultActivity
import com.example.fucai4.ResultActivity2
import com.example.fucai4.base.BaseMVPFragment
import com.example.fucai4.utlis.Contf
import com.example.fucai4.utlis.GongYong_Zuhao
import com.example.fucai4.utlis.PreferencesUtil
import com.example.sfjdpt.adapter.BaseRecyclerAdapter
import com.example.sfjdpt.adapter.SmartViewHolder

/**
 * Created by 42224 on 2018/7/5.
 */
class TicaiFragment : BaseMVPFragment() {

    var tiRecyclerView3: RecyclerView? = null
    var tiRecyclerView6: RecyclerView? = null


    var mAdapter6: BaseRecyclerAdapter<String>? = null
    var mAdapter3: BaseRecyclerAdapter<String>? = null


    var ti_et_shang: EditText? = null //上期开奖号码

    var ti_zhongyao: TextView? = null
    var ti_zhongyao_et: EditText? = null //重要条件

    //组6判断条件
    var ti_et_dan: EditText? = null //胆码
    var ti_et_sha: EditText? = null //杀码
    var ti_et_liang: EditText? = null //两码
    var ti_et_danzu: EditText? = null //胆组
    var ti_et_kua: EditText? = null //跨度
    var ti_et_he: EditText? = null //和尾值
    var ti_et_duan: EditText? = null //断组
    var ti_et_fu: EditText? = null //复式
    var ti_et_shazuhe: EditText? = null //杀组合


    //组3判断条件
    var ti_et_dan3: EditText? = null //胆码
//    var ti_et_duan3: EditText? = null //断组
    var ti_et_shazuhe3: EditText? = null //断组



    var but_saixuan: Button? = null

    var shang: String = ""
    var s1: String = ""


    var a: MutableList<String> = ArrayList()//原始值 组6
    var a6: MutableList<String> = ArrayList()//原始值 组6
    var t6: MutableList<String> = ArrayList() //得出的组6值

    var b: MutableList<String> = ArrayList()//原始值 组3
    var b3: MutableList<String> = ArrayList()//原始值 组3
    var t3: MutableList<String> = ArrayList() //得出的组3值

    var string_array6: Array<String>? = null
    var string_array2: Array<String>? = null
    var number: Int = 0
    var number2: Int = 0

    var mBoolean: Boolean = false
    var mBoolean2: Boolean = false

    override fun getLayoutId(): Int {
        return R.layout.fm_ticai
    }

    override fun findViews(view: View?) {
        tiRecyclerView3 = view?.findViewById(R.id.tiRecyclerView3)
        tiRecyclerView6 = view?.findViewById(R.id.tiRecyclerView6)
        but_saixuan = view?.findViewById(R.id.but_saixuan)
        ti_et_shang = view?.findViewById(R.id.ti_et_shang)
        ti_zhongyao = view?.findViewById(R.id.ti_zhongyao)
        ti_zhongyao_et = view?.findViewById(R.id.ti_zhongyao_et)
        ti_et_dan = view?.findViewById(R.id.ti_et_dan)
        ti_et_sha = view?.findViewById(R.id.ti_et_sha)
        ti_et_liang = view?.findViewById(R.id.ti_et_liang)
        ti_et_danzu = view?.findViewById(R.id.ti_et_danzu)
        ti_et_kua = view?.findViewById(R.id.ti_et_kua)
        ti_et_he = view?.findViewById(R.id.ti_et_he)
        ti_et_duan = view?.findViewById(R.id.ti_et_duan)
        ti_et_fu = view?.findViewById(R.id.ti_et_fu)
        ti_et_dan3 = view?.findViewById(R.id.ti_et_dan3)
//        ti_et_duan3 = view?.findViewById(R.id.ti_et_duan3)
        ti_et_shazuhe3 = view?.findViewById(R.id.ti_et_shazuhe3)
        ti_et_shazuhe = view?.findViewById(R.id.ti_et_shazuhe)

    }

    override fun setViews() {
        setTextColor(ti_zhongyao!!)
        mBoolean = PreferencesUtil.get(context, Contf.BOOLEANTI6, false) as Boolean
        mBoolean2 = PreferencesUtil.get(context, Contf.BOOLEANTI3, false) as Boolean

        tiRecyclerView6?.layoutManager = GridLayoutManager(context, 7)
        tiRecyclerView3?.layoutManager = GridLayoutManager(context, 7)
        mAdapter6 = object : BaseRecyclerAdapter<String>(R.layout.item_recyc) {
            override fun onBindViewHolder(holder: SmartViewHolder, model: String?, position: Int) {
                holder.text(R.id.recyc_tv, model!!)
            }
        }
        mAdapter3 = object : BaseRecyclerAdapter<String>(R.layout.item_recyc) {
            override fun onBindViewHolder(holder: SmartViewHolder, model: String?, position: Int) {
                holder.text(R.id.recyc_tv, model!!)
            }
        }
        tiRecyclerView6?.adapter = mAdapter6
        tiRecyclerView3?.adapter = mAdapter3

        but_saixuan?.setOnClickListener {
            a.clear()
            a6.clear()
            t6.clear()
            b.clear()
            b3.clear()
            t3.clear()
            setHuanChun()
            shang = ti_et_shang?.text.toString()
            if (shang.length == 3) {
                if (ti_zhongyao_et?.text.toString().length == 5) {
                    if (!GongYong_Zuhao.containRepeatChar(ti_zhongyao_et?.text.toString())) {
                        var s = GongYong_Zuhao.CalcBama(shang, 0, false)
                        s1 = s[0].toString()
                        var tiaojian = GongYong_Zuhao.zhongyaocanshu(ti_zhongyao_et?.text.toString())
                        var zu6 = GongYong_Zuhao.xinzu6(s[0], tiaojian, s[1])
                        a6 = bubbleSort(zu6)
                        a = a6
                        string_array6 = arrayOf(
                                ti_et_dan?.text.toString(),
                                ti_et_liang?.text.toString(),
                                ti_et_he?.text.toString(),
                                ti_et_kua?.text.toString(),
                                ti_et_fu?.text.toString(),
                                ti_et_duan?.text.toString(),
                                ti_et_danzu?.text.toString(),
                                ti_et_sha?.text.toString(),
                                ti_et_shazuhe?.text.toString()
                        )

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
                                } else {
                                    number++
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
                                } else {
                                    number++
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
                                } else {
                                    number++
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
                                } else {
                                    number++
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
                                } else {
                                    number++
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
                                } else {
                                    number++
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
                                        Toast.makeText(context, "最少输入2组胆组,最多输入3组胆组", Toast.LENGTH_SHORT).show()
                                        return@setOnClickListener
                                    }
                                } else {
                                    number++
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
                                } else {
                                    number++
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
                                } else {
                                    number++
                                }
                            }
                            t6 = ArrayList()
                        }
                        if (number < 9) {

                            var lin_b3 = GongYong_Zuhao.Zuhao3New1(s1)
                            b3 = lin_b3[0]
                            b = b3
                            string_array2 = arrayOf(ti_et_dan3?.text.toString(),  ti_et_shazuhe3?.text.toString())


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
                                    } else {
                                        number2++
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
                                    } else {
                                        number2++
                                    }
                                }
                                t3 = ArrayList()
                            }
                            if (number2 <2) {


                                var intent = Intent(context, ResultActivity2::class.java)
                                intent.putExtra("type", "1")
                                intent.putStringArrayListExtra("t6", a as java.util.ArrayList<String>?)
                                intent.putStringArrayListExtra("t3", b as java.util.ArrayList<String>?)
                                intent.putStringArrayListExtra("a6", a6 as java.util.ArrayList<String>?)
                                intent.putStringArrayListExtra("b3", b3 as java.util.ArrayList<String>?)
                                intent.putExtra("s1", s1)
                                val b = Bundle()
                                b.putStringArray("string_array6", string_array6)
                                b.putStringArray("string_array2", string_array2)
                                intent.putExtras(b)
                                intent.putExtra("shang", shang)
                                intent.putExtra("ti_zhongyao_et", ti_zhongyao_et!!.text.toString())
                                intent.putExtra("shuoming", lin_b3[2].toString())
                                startActivity(intent)

                                number2 = 0
                            } else {
                                Toast.makeText(context, "组3必须输入一个条件", Toast.LENGTH_SHORT).show()
                                b.clear()

                                number2 = 0
                                return@setOnClickListener
                            }



                            number = 0
                        } else {
                            Toast.makeText(context, "组6必须输入一个条件", Toast.LENGTH_SHORT).show()
                            number = 0
                            a.clear()
                            mAdapter6?.notifyDataSetChanged()
                            return@setOnClickListener
                        }

                    } else {
                        Toast.makeText(context, "重要参数不能有重复的数字", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "重要参数一定要输入5位数字", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "只能输入3位数字", Toast.LENGTH_SHORT).show()
            }
        }
    }



    override fun getData() {
        if (mBoolean) {
            setview(Contf.ti_et_dan, ti_et_dan!!)
            setview(Contf.ti_et_liang, ti_et_liang!!)
            setview(Contf.ti_et_he, ti_et_he!!)
            setview(Contf.ti_et_kua, ti_et_kua!!)
            setview(Contf.ti_et_fu, ti_et_fu!!)
            setview(Contf.ti_et_duan, ti_et_duan!!)
            setview(Contf.ti_et_danzu, ti_et_danzu!!)
            setview(Contf.ti_et_sha, ti_et_sha!!)
            setview(Contf.ti_et_shang, ti_et_shang!!)
            setview(Contf.ti_et_shazuhe, ti_et_shazuhe!!)
            setview(Contf.ti_zhongyao_et, ti_zhongyao_et!!)
            setview(Contf.ti_et_shazuhe3, ti_et_shazuhe3!!)
        }
        if (mBoolean2) {
            setview(Contf.ti_et_dan3, ti_et_dan3!!)

            setview(Contf.ti_et_shazuhe3, ti_et_shazuhe3!!)
        }
    }

    fun setTextColor(textView: TextView) {
        var builder = SpannableStringBuilder(textView.text.toString())
        //ForegroundColorSpan 为文字前景色
        // BackgroundColorSpan为文字背景色
        var redSpan = ForegroundColorSpan(Color.RED)
        builder.setSpan(redSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = builder
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
        var sss: ArrayList<String> = ArrayList()
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

    fun setview(kay: String, editText: EditText) {
        var msg: String = PreferencesUtil.get(context, kay, "").toString()
        if (!TextUtils.isEmpty(msg)) {
            editText.setText(msg)
        }
    }

    fun setPutRemove(key: String, vlaue: String) {
        PreferencesUtil.remove(context, key)
        PreferencesUtil.put(context, key, vlaue)
    }

    private fun setHuanChun() {
        if (mBoolean) {
            setPutRemove(Contf.ti_et_dan, ti_et_dan!!.text.toString())
            setPutRemove(Contf.ti_et_liang, ti_et_liang!!.text.toString())
            setPutRemove(Contf.ti_et_he, ti_et_he!!.text.toString())
            setPutRemove(Contf.ti_et_kua, ti_et_kua!!.text.toString())
            setPutRemove(Contf.ti_et_fu, ti_et_fu!!.text.toString())
            setPutRemove(Contf.ti_et_duan, ti_et_duan!!.text.toString())
            setPutRemove(Contf.ti_et_danzu, ti_et_danzu!!.text.toString())
            setPutRemove(Contf.ti_et_sha, ti_et_sha!!.text.toString())
            setPutRemove(Contf.ti_et_dan3, ti_et_dan3!!.text.toString())

            setPutRemove(Contf.ti_et_shang, ti_et_shang!!.text.toString())
            setPutRemove(Contf.ti_et_shazuhe, ti_et_shazuhe!!.text.toString())
            setPutRemove(Contf.ti_zhongyao_et, ti_zhongyao_et!!.text.toString())
        } else {
            PreferencesUtil.put(context, Contf.BOOLEANTI6, true)
            PreferencesUtil.put(context, Contf.ti_et_shang, ti_et_shang!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_et_dan, ti_et_dan!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_et_liang, ti_et_liang!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_et_he, ti_et_he!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_et_kua, ti_et_kua!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_et_fu, ti_et_fu!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_et_duan, ti_et_duan!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_et_danzu, ti_et_danzu!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_et_sha, ti_et_sha!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_et_shazuhe, ti_et_shazuhe!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_zhongyao_et, ti_zhongyao_et!!.text.toString())
        }

        if (mBoolean2) {
            setPutRemove(Contf.ti_et_dan3, ti_et_dan3!!.text.toString())

            setPutRemove(Contf.ti_et_shazuhe3, ti_et_shazuhe3!!.text.toString())
        } else {
            PreferencesUtil.put(context, Contf.BOOLEANTI3, true)
            PreferencesUtil.put(context, Contf.ti_et_dan3, ti_et_dan3!!.text.toString())
            PreferencesUtil.put(context, Contf.ti_et_shazuhe3, ti_et_shazuhe3!!.text.toString())
        }
    }
}