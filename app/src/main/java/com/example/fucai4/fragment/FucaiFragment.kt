package com.example.fucai4.fragment

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
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
import com.example.fucai4.base.BaseMVPFragment
import com.example.fucai4.utlis.Contf
import com.example.fucai4.utlis.GongYong_Zuhao
import com.example.fucai4.utlis.PreferencesUtil
import com.example.sfjdpt.adapter.BaseRecyclerAdapter
import com.example.sfjdpt.adapter.SmartViewHolder

/**
 *
 * Created by 42224 on 2018/7/5.
 */
class FucaiFragment : BaseMVPFragment() {

    var mAdapter6: BaseRecyclerAdapter<String>? = null
    var mAdapter3: BaseRecyclerAdapter<String>? = null

    var mian_et_shang1: EditText? = null

    var mRecyclerView6: RecyclerView? = null
    var mRecyclerView3: RecyclerView? = null

    var mian_et_dan6: EditText? = null
    var mian_et_sha6: EditText? = null
    var mian_et_liang6: EditText? = null
    var mian_et_danzu6: EditText? = null


    var mian_et_duan6: EditText? = null
    var mian_et_fu6: EditText? = null

    var mian_et_can1: EditText? = null
    var mian_et_can2: EditText? = null
    var mian_et_can3: EditText? = null


    var but_saixuan: Button? = null

    var mian_et_dan3: EditText? = null
    var mian_et_duan3: EditText? = null
    var mian_et_shazuhe3: EditText? = null


    var string_array6: Array<String>? = null
    var string_array2: Array<String>? = null

    var but_saixuan6: Button? = null


    var a: MutableList<String> = ArrayList()//原始值 组6
    var a6: MutableList<String> = ArrayList()//原始值 组6
    var t6: MutableList<String> = ArrayList() //得出的组6值

    var b: MutableList<String> = ArrayList()//原始值 组3
    var b3: MutableList<String> = ArrayList()//原始值 组3
    var t3: MutableList<String> = ArrayList() //得出的组3值


    var shang: String = ""
    var s1: String? = null

    var number: Int = 0
    var number2: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.fm_fucai
    }

    override fun findViews(view: View?) {
        mRecyclerView6 = view?.findViewById(R.id.mRecyclerView6)
        mRecyclerView3 = view?.findViewById(R.id.mRecyclerView3)

        mian_et_dan6 = view?.findViewById(R.id.mian_et_dan6)
        mian_et_sha6 = view?.findViewById(R.id.mian_et_sha6)
        mian_et_liang6 = view?.findViewById(R.id.mian_et_liang6)
        mian_et_danzu6 = view?.findViewById(R.id.mian_et_danzu6)

        mian_et_duan6 = view?.findViewById(R.id.mian_et_duan6)
        mian_et_fu6 = view?.findViewById(R.id.mian_et_fu6)
        but_saixuan = view?.findViewById(R.id.but_saixuan)
        mian_et_dan3 = view?.findViewById(R.id.mian_et_dan3)
        mian_et_duan3 = view?.findViewById(R.id.mian_et_duan3)
        mian_et_shazuhe3 = view?.findViewById(R.id.mian_et_shazuhe3)
        mian_et_shang1 = view?.findViewById(R.id.mian_et_shang1)


        but_saixuan6 = view?.findViewById(R.id.but_saixuan6)

        mian_et_can1 = view?.findViewById(R.id.mian_et_can1)
        mian_et_can2 = view?.findViewById(R.id.mian_et_can2)
        mian_et_can3 = view?.findViewById(R.id.mian_et_can3)


    }

    override fun setViews() {
        mBoolean = PreferencesUtil.get(context, Contf.BOOLEAN, false) as Boolean
        mBoolean2 = PreferencesUtil.get(context, Contf.BOOLEAN3, false) as Boolean

        mRecyclerView6?.layoutManager = GridLayoutManager(context, 7)
        mRecyclerView3?.layoutManager = GridLayoutManager(context, 7)

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


        mRecyclerView6?.adapter = mAdapter6
        mRecyclerView3?.adapter = mAdapter3

        but_saixuan6?.setOnClickListener {

            setHuanChun()

            a6.clear()
            a.clear()
            t6.clear()

            b.clear()
            b3.clear()
            t3.clear()

            shang = mian_et_shang1?.text.toString()
            if (shang.length == 3) {
                if (mian_et_can1?.text.toString().length != 4) {
                    Toast.makeText(context, "参数一只能输入4位数", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (mian_et_can2?.text.toString().length != 3) {
                    Toast.makeText(context, "参数二只能输入3位数", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (mian_et_can3?.text.toString().length != 3) {
                    Toast.makeText(context, "参数三只能输入3位数", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (!GongYong_Zuhao.can(mian_et_can1?.text.toString(), mian_et_can2?.text.toString(), mian_et_can3?.text.toString())) {
                    Toast.makeText(context, "三个参数的内容不允许有相同的数字", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                var lin_a6 = GongYong_Zuhao.zu6(mian_et_can1?.text.toString(), mian_et_can2?.text.toString(), mian_et_can3?.text.toString())

                string_array6 = arrayOf(
                        mian_et_dan6?.text.toString(),
                        mian_et_liang6?.text.toString(),
                        mian_et_fu6?.text.toString(),
                        mian_et_duan6?.text.toString(),
                        mian_et_danzu6?.text.toString(),
                        mian_et_sha6?.text.toString()
                )
                lin_a6 = bubbleSort(lin_a6)

                var s = GongYong_Zuhao.CalcBama(shang, 0, true)
                s1 = s[0].toString()
                var lin_b3 = GongYong_Zuhao.Zuhao3New2(s1)
                var shuoming: String = lin_b3.get(1).toString()
                Log.e("shuoming------------11-", shuoming)

                var condition = lin_b3[2].toString()
                condition = condition.replace("[", "")
                condition = condition.replace("]", "")
                Log.e("condition--------------", condition)

                /*  for (i in lin_a6.indices) {
                      if (!GongYong_Zuhao.test(lin_a6[i], condition, 5)) a6.add(lin_a6[i])
                  }
                  Log.e("新组6数据", a6.toString())
                  Log.e("新组6数据长度", "" + a6.size) a = a6
                  */

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
                    if (i == 2) { //复式执行
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
                    if (i == 3) { //断组执行
                        var kua_text = value
                        // Log.e("输入的断组：", kua_text)
                        if (!kua_text.equals("")) {
                            for (i in a.indices) {
                                if (!GongYong_Zuhao.test(a[i], kua_text, 6)) t6.add(a[i])
                            }
                            a = t6
                        } else {
                            number++
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
                                Toast.makeText(context, "最少输入2组胆组,最多输入3组胆组", Toast.LENGTH_SHORT).show()
                                return@setOnClickListener
                            }

                        } else {
                            number++
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
                        } else {
                            number++
                        }
                    }
                    t6 = ArrayList()
                }
                Log.e("number", "" + number)
                if (number < 6) {

                    b3 = lin_b3[0]
                    b = b3
                    string_array2 = arrayOf(mian_et_dan3?.text.toString(), mian_et_duan3?.text.toString(), mian_et_shazuhe3?.text.toString())
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
                            } else {
                                number2++
                            }
                        }
                        if (i == 1) { //断组执行
                            // Log.e("输入的断组：", kua_text)
                            if (value != "") {
                                for (i in b.indices) {
                                    if (!GongYong_Zuhao.test(b[i], value, 6)) t3.add(b[i])
                                }
                                b = t3
                            } else {
                                number2++
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
                            } else {
                                number2++
                            }
                        }
                        t3 = ArrayList()
                    }
                    if (number2 < 3) {
                        var intent = Intent(context, ResultActivity::class.java)
                        intent.putExtra("type", "0")
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
                        intent.putExtra("mian_et_can1", mian_et_can1!!.text.toString())
                        intent.putExtra("mian_et_can2", mian_et_can2!!.text.toString())
                        intent.putExtra("mian_et_can3", mian_et_can3!!.text.toString())
                        intent.putExtra("shuoming", shuoming)
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
                    return@setOnClickListener
                }

            } else {
                Toast.makeText(context, "只能输入3位数字", Toast.LENGTH_SHORT).show()
            }
        }
    }

    var mBoolean: Boolean = false
    var mBoolean2: Boolean = false
    override fun getData() {

        if (mBoolean) {
            setview(Contf.mian_et_dan6, mian_et_dan6!!)
            setview(Contf.mian_et_liang6, mian_et_liang6!!)
            setview(Contf.mian_et_fu6, mian_et_fu6!!)
            setview(Contf.mian_et_duan6, mian_et_duan6!!)
            setview(Contf.mian_et_danzu6, mian_et_danzu6!!)
            setview(Contf.mian_et_sha6, mian_et_sha6!!)
            setview(Contf.mian_fu_shang, mian_et_shang1!!)
            setview(Contf.can1, mian_et_can1!!)
            setview(Contf.can2, mian_et_can2!!)
            setview(Contf.can3, mian_et_can3!!)

        }
        if (mBoolean2) {
            setview(Contf.mian_et_dan3, mian_et_dan3!!)
            setview(Contf.mian_et_duan3, mian_et_duan3!!)
            setview(Contf.mian_et_shazuhe3, mian_et_shazuhe3!!)
        }
    }

    fun setTextColor(textView: TextView) {
        var builder = SpannableStringBuilder(textView.text.toString())
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        var redSpan = ForegroundColorSpan(Color.RED)
        builder.setSpan(redSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.setText(builder)
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
            setPutRemove(Contf.mian_et_dan6, mian_et_dan6!!.text.toString())
            setPutRemove(Contf.mian_et_liang6, mian_et_liang6!!.text.toString())

            setPutRemove(Contf.mian_et_fu6, mian_et_fu6!!.text.toString())
            setPutRemove(Contf.mian_et_duan6, mian_et_duan6!!.text.toString())
            setPutRemove(Contf.mian_et_danzu6, mian_et_danzu6!!.text.toString())
            setPutRemove(Contf.mian_et_sha6, mian_et_sha6!!.text.toString())
            setPutRemove(Contf.mian_et_dan3, mian_et_dan3!!.text.toString())
            setPutRemove(Contf.mian_et_duan3, mian_et_duan3!!.text.toString())
            setPutRemove(Contf.mian_fu_shang, mian_et_shang1!!.text.toString())

            setPutRemove(Contf.can1, mian_et_can1!!.text.toString())
            setPutRemove(Contf.can2, mian_et_can2!!.text.toString())
            setPutRemove(Contf.can3, mian_et_can3!!.text.toString())
        } else {
            PreferencesUtil.put(context, Contf.BOOLEAN, true)
            PreferencesUtil.put(context, Contf.mian_fu_shang, mian_et_shang1!!.text.toString())
            PreferencesUtil.put(context, Contf.mian_et_dan6, mian_et_dan6!!.text.toString())
            PreferencesUtil.put(context, Contf.mian_et_liang6, mian_et_liang6!!.text.toString())

            PreferencesUtil.put(context, Contf.mian_et_fu6, mian_et_fu6!!.text.toString())
            PreferencesUtil.put(context, Contf.mian_et_duan6, mian_et_duan6!!.text.toString())
            PreferencesUtil.put(context, Contf.mian_et_danzu6, mian_et_danzu6!!.text.toString())
            PreferencesUtil.put(context, Contf.mian_et_sha6, mian_et_sha6!!.text.toString())

            PreferencesUtil.put(context, Contf.can1, mian_et_can1!!.text.toString())
            PreferencesUtil.put(context, Contf.can2, mian_et_can2!!.text.toString())
            PreferencesUtil.put(context, Contf.can3, mian_et_can3!!.text.toString())

        }

        if (mBoolean2) {
            setPutRemove(Contf.mian_et_dan3, mian_et_dan3!!.text.toString())
            setPutRemove(Contf.mian_et_duan3, mian_et_duan3!!.text.toString())
            setPutRemove(Contf.mian_et_shazuhe3, mian_et_shazuhe3!!.text.toString())
        } else {
            PreferencesUtil.put(context, Contf.BOOLEAN3, true)
            PreferencesUtil.put(context, Contf.mian_et_dan3, mian_et_dan3!!.text.toString())
            PreferencesUtil.put(context, Contf.mian_et_duan3, mian_et_duan3!!.text.toString())
            PreferencesUtil.put(context, Contf.mian_et_shazuhe3, mian_et_shazuhe3!!.text.toString())
        }
    }
}