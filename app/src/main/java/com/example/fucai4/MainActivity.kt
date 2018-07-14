package com.example.fucai4

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.text.TextUtils
import android.util.Log

import android.widget.Toast

import android.content.Intent
import android.widget.TextView
import android.graphics.Color
import android.support.v4.app.Fragment
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.EditText
import com.example.fucai4.api.AppClient
import com.example.fucai4.api.Services
import com.example.fucai4.fragment.FucaiFragment
import com.example.fucai4.fragment.TicaiFragment
import com.example.fucai4.utlis.Contf
import com.example.fucai4.utlis.PreferencesUtil
import com.example.fucai4.view.NetworkDialog
import kotlinx.android.synthetic.main.activity_main1.*

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


class MainActivity : FragmentActivity() {

    var a: MutableList<String> = ArrayList()//原始值 组6
    var b: MutableList<String> = ArrayList()//原始值 组3

    var a6: MutableList<String> = ArrayList()//原始值 组6
    var b3: MutableList<String> = ArrayList()//原始值 组3

    var t3: MutableList<String> = ArrayList() //得出的组3值
    var t6: MutableList<String> = ArrayList() //得出的组6值

    var string_array: Array<String>? = null

    var number: Int = 0
    var shang: String = ""
    var s1: String? = null
    var MIYAO: String? = null
    var JIQI: String? = null

    var networkDialog: NetworkDialog? = null
    var myViewPageAdapter: MyViewPageAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        var titleDatas = ArrayList<String>()
        titleDatas.add("福彩")
        titleDatas.add("体彩")
        var fragmentList = ArrayList<Fragment>()
        fragmentList.add(FucaiFragment())
        fragmentList.add(TicaiFragment())
        myViewPageAdapter = MyViewPageAdapter(supportFragmentManager, titleDatas, fragmentList)

        MIYAO = PreferencesUtil.get(this@MainActivity, Contf.MIYAO, "").toString()
        JIQI = PreferencesUtil.get(this@MainActivity, Contf.JIQI, "").toString()
        Log.e("机器，", JIQI + "")
        Log.e("密钥，", MIYAO + "")
        networkDialog = NetworkDialog(this@MainActivity)
//
        initWangLuo()


//
//        var mBoolean: Boolean = PreferencesUtil.get(this@MainActivity, Contf.BOOLEAN, false) as Boolean
//        if (mBoolean) {
//            setview(Contf.mian_et_dan, mian_et_dan)
//            setview(Contf.mian_et_liang, mian_et_liang)
//            setview(Contf.mian_et_he, mian_et_he)
//            setview(Contf.mian_et_kua, mian_et_kua)
//            setview(Contf.mian_et_fu, mian_et_fu)
//            setview(Contf.mian_et_duan, mian_et_duan)
//
//            setview(Contf.mian_et_danzu, mian_et_danzu)
//            setview(Contf.mian_et_shang, mian_et_shang)
//            setview(Contf.mian_et_sha, mian_et_sha)
//
//        }
//        initWangLuo()
//        but_saixuan.setOnClickListener { v: View? ->
//            a.clear()
//            b.clear()
//            a6.clear()
//            b3.clear()
//            t3.clear()
//            t6.clear()
//            number = 0
//            shang = mian_et_shang.text.toString()
//            if (shang.length == 3) {
//                var s = GongYong_Zuhao.CalcBama(shang, 0,true)
//                s1 = s[0].toString()
//                Log.e("s1", s1.toString())
//                Log.e("s", s.toString())
//
////                var lin_a6 = GongYong_Zuhao.zu6( )
//                var lin_a6 = ArrayList<String>()
//
//                Log.e(" 组6未过滤的原始值", lin_a6.toString())
//                var lin_b3 = GongYong_Zuhao.Zuhao3New1(s1)
//                Log.e("组3的原始值，和组6判断条件", lin_b3.toString())
//                b3 = lin_b3.get(0)
//                var shuoming: String = lin_b3.get(1).toString()
//                Log.e("shuoming------------11-", shuoming)
//                var condition = lin_b3.get(2).toString()
//                condition = condition.replace("[", "")
//                condition = condition.replace("]", "")
//                Log.e("condition--------------", condition)
//
//                lin_a6 = bubbleSort(lin_a6)
//
////                a6 = lin_a6
//
//                for (i in lin_a6.indices) {
//                    if (!GongYong_Zuhao.test(lin_a6[i], condition, 5)) a6.add(lin_a6[i])
//                }
//
//                Log.e("新A6数据", a6.toString())
//                Log.e("新A6数据长度", "" + a6.size)
//                Log.e("新b3数据", b3.toString())
//                Log.e("新b3数据长度", "" + b3.size)
//
////                a6 = GongYong_Zuhao.Zuhao6New(s1)
//
//
//                a = a6
//                b = b3
//
//                string_array = arrayOf(
//                        mian_et_dan.text.toString(),
//                        mian_et_liang.text.toString(),
//                        mian_et_he.text.toString(),
//                        mian_et_kua.text.toString(),
//                        mian_et_fu.text.toString(),
//                        mian_et_duan.text.toString(),
//                        mian_et_danzu.text.toString(),
//                        mian_et_sha.text.toString()
//                )
//                for (i in string_array!!.indices) {
//                    var value = string_array!![i]
//                    if (i == 0) { //胆码执行
//                        var danma_text = value.replace("-", "")
//                        if (!danma_text.equals("")) {
//                            for (item in a) {
//                                if (GongYong_Zuhao.test(item, danma_text, 1)) {
//                                    t6.add(item);
//                                }
//                            }
//                            a = t6;
//                            for (item in b) {
//                                if (GongYong_Zuhao.test(item, danma_text, 1)) {
//                                    t3.add(item)
//                                }
//                            }
//                            b = t3
//                        } else {
//                            number++
//                        }
//                    }
//
//                    if (i == 1) { //两码执行
//                        var erma_text = value//.split("-", "")
//                        Log.e("输入的两码值：", erma_text.toString())
//                        if (!erma_text.equals("")) {
//                            for (i in a.indices) {
//                                if (GongYong_Zuhao.test(a[i], erma_text, 2)) t6.add(a[i])
//                            }
//                            a = t6;
//                            for (i in b.indices) {
//                                if (GongYong_Zuhao.test(b[i], erma_text, 2)) t3.add(b[i]);
//                            }
//                            b = t3;
//                        } else {
//                            number++
//                        }
//                    }
//                    if (i == 2) { //和尾值执行
//                        var he_text = value.replace("-", "")
//                        Log.e("输入的和尾值：", he_text)
//                        if (!he_text.equals("")) {
//                            for (i in a.indices) {
//                                if (GongYong_Zuhao.test(a[i], he_text, 3)) t6.add(a[i])
//                            }
//                            a = t6;
//                            for (i in b.indices) {
//                                if (GongYong_Zuhao.test(b[i], he_text, 3)) t3.add(b[i]);
//                            }
//                            b = t3;
//                        } else {
//                            number++
//                        }
//                    }
//                    if (i == 3) { //跨度执行
//                        var kua_text = value.replace("-", "")
//                        Log.e("输入的跨度：", kua_text)
//                        if (!kua_text.equals("")) {
//                            for (i in a.indices) {
//                                if (GongYong_Zuhao.test(a[i], kua_text, 4)) t6.add(a[i])
//                            }
//                            a = t6;
//                            for (i in b.indices) {
//                                if (GongYong_Zuhao.test(b[i], kua_text, 4)) t3.add(b[i]);
//                            }
//                            b = t3;
//                        } else {
//                            number++
//                        }
//                    }
//                    if (i == 4) { //复式执行
//                        var kua_text = value//.replace("-", "")
//                        Log.e("输入的复式：", kua_text)
//                        if (!kua_text.equals("")) {
//
//                            for (i in a.indices) {
//                                if (GongYong_Zuhao.test(a[i], kua_text, 5)) t6.add(a[i])
//                            }
//                            a = t6
//                            for (i in b.indices) {
//                                if (GongYong_Zuhao.test(b[i], kua_text, 5)) t3.add(b[i]);
//                            }
//                            b = t3
//                        } else {
//                            number++
//                        }
//                    }
//                    if (i == 5) { //断组执行
//                        var kua_text = value
//                        // Log.e("输入的断组：", kua_text)
//                        if (!kua_text.equals("")) {
//                            for (i in a.indices) {
//                                if (!GongYong_Zuhao.test(a[i], kua_text, 6)) t6.add(a[i])
//                            }
//                            a = t6
//                            for (i in b.indices) {
//                                if (!GongYong_Zuhao.test(b[i], kua_text, 6)) t3.add(b[i])
//                            }
//                            b = t3
//                        } else {
//                            number++
//                        }
//                    }
//
//                    if (i == 6) { //胆组执行
//                        if (!value.equals("")) {
//                            var danzu_text = value.split("-")
//                            if (danzu_text.size > 1 && danzu_text.size <= 3) {
//
//                                var newd1: MutableList<String> = ArrayList()
//                                var newd2: MutableList<String> = ArrayList()
//                                var newd3: MutableList<String> = ArrayList()
//
//                                var d1: MutableList<String> = ArrayList()
//                                var d2: MutableList<String> = ArrayList()
//                                var newd1_1: MutableList<String> = ArrayList()
//                                var newd2_2: MutableList<String> = ArrayList()
//                                var newd3_3: MutableList<String> = ArrayList()
//
//                                if (!danzu_text.equals("")) {
//                                    for (item in a) {
//                                        for (x in danzu_text!!.indices) {
//                                            if (GongYong_Zuhao.test(item, danzu_text[x], 1)) {
//                                                if (x == 0) newd1.add(item)
//                                                if (x == 1) newd2.add(item)
//                                                if (x == 2) newd3.add(item)
//                                                if (danzu_text.size == 1) t6.add(item)
//                                            }
//                                        }
//                                    }
//                                    for (n1 in newd1) {
//                                        for (n2 in newd2) {
//                                            if (n2 == n1) {
//                                                d1.add(n2)
//                                            }
//                                        }
//                                    }
//                                    for (n in d1) {
//                                        if (newd3.size != 0) {
//                                            for (n3 in newd3) {
//                                                if (n3 == n) {
//                                                    t6.add(n3)
//                                                }
//                                            }
//                                        } else {
//                                            t6.add(n)
//                                        }
//                                    }
//                                    a = t6;
//                                    for (item in b) {
//                                        for (x in danzu_text!!.indices) {
//                                            if (GongYong_Zuhao.test(item, danzu_text[x], 1)) {
//                                                if (x == 0) newd1_1.add(item)
//                                                if (x == 1) newd2_2.add(item)
//                                                if (x == 2) newd3_3.add(item)
//                                                if (danzu_text.size == 1) t3.add(item)
//                                                // t3.add(item)
//                                            }
//                                        }
//                                    }
//                                    for (n1 in newd1_1) {
//                                        for (n2 in newd2_2) {
//                                            if (n2 == n1) {
//                                                d2.add(n2)
//                                            }
//                                        }
//                                    }
//                                    for (n in d2) {
//                                        if (newd3_3.size != 0) {
//                                            for (n3 in newd3_3) {
//                                                if (n3 == n) {
//                                                    t3.add(n3)
//                                                }
//                                            }
//                                        } else {
//                                            t3.add(n)
//                                        }
//                                    }
//                                    b = t3
//                                }
//                            } else {
//                                Toast.makeText(this@MainActivity, "最少输入2组胆组,最多输入3组胆组", Toast.LENGTH_SHORT).show()
//                                return@setOnClickListener
//                            }
//
//                        } else {
//                            number++
//                        }
//                    }
//                    if (i == 7) {
//                        var danma_text = value.replace("-", "")
//                        if (!danma_text.equals("")) {
//                            for (item in a) {
//                                if (GongYong_Zuhao.test(item, danma_text, 7)) {
//                                    t6.add(item)
//                                }
//                            }
//                            a = t6;
//                            for (item in b) {
//                                if (GongYong_Zuhao.test(item, danma_text, 1)) {
//                                    t3.add(item)
//                                }
//                            }
//                            b = t3
//                        } else {
//                            number++
//                        }
//                    }
//                    t6 = ArrayList()
//                    t3 = ArrayList()
//                }
//                Log.e("11111", "" + number)
//
//                if (number < 7) {
//                    if (mBoolean) {
//
//                        PreferencesUtil.remove(this@MainActivity, Contf.mian_et_shang)
//                        PreferencesUtil.remove(this@MainActivity, Contf.mian_et_dan)
//                        PreferencesUtil.remove(this@MainActivity, Contf.mian_et_liang)
//                        PreferencesUtil.remove(this@MainActivity, Contf.mian_et_he)
//                        PreferencesUtil.remove(this@MainActivity, Contf.mian_et_kua)
//                        PreferencesUtil.remove(this@MainActivity, Contf.mian_et_fu)
//                        PreferencesUtil.remove(this@MainActivity, Contf.mian_et_duan)
//                        PreferencesUtil.remove(this@MainActivity, Contf.mian_et_danzu)
//                        PreferencesUtil.remove(this@MainActivity, Contf.mian_et_sha)
//
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_shang, mian_et_shang.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_dan, mian_et_dan.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_liang, mian_et_liang.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_he, mian_et_he.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_kua, mian_et_kua.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_fu, mian_et_fu.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_duan, mian_et_duan.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_danzu, mian_et_danzu.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_sha, mian_et_sha.text.toString())
//
//
//                    } else {
//                        PreferencesUtil.put(this@MainActivity, Contf.BOOLEAN, true)
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_shang, mian_et_shang.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_dan, mian_et_dan.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_liang, mian_et_liang.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_he, mian_et_he.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_kua, mian_et_kua.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_fu, mian_et_fu.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_duan, mian_et_duan.text.toString())
//                        PreferencesUtil.put(this@MainActivity, Contf.mian_et_danzu, mian_et_danzu.text.toString())
//                    }
//                    var intent = Intent(this@MainActivity, ResultActivity::class.java)
//                    intent.putStringArrayListExtra("t6", a as java.util.ArrayList<String>?)
//                    intent.putStringArrayListExtra("t3", b as java.util.ArrayList<String>?)
//                    intent.putStringArrayListExtra("a6", a6 as java.util.ArrayList<String>?)
//                    intent.putStringArrayListExtra("b3", b3 as java.util.ArrayList<String>?)
//                    intent.putExtra("s1", s1)
//                    val b = Bundle()
//                    b.putStringArray("string_array", string_array)
//                    intent.putExtras(b)
//                    intent.putExtra("shang", shang)
//                    intent.putExtra("shuoming", shuoming)
//
//                    number = 0
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(this@MainActivity, "至少输入一个条件", Toast.LENGTH_SHORT).show()
//                    number = 0
//                    return@setOnClickListener
//                }
//            } else {
//                Toast.makeText(this@MainActivity, "上期彩票必须输入三位数", Toast.LENGTH_SHORT).show()
//            }
//        }

//        mian_tv_jiao.setOnClickListener { v: View? ->
//            var intent = Intent(this@MainActivity, IntersectionActivity::class.java)
//            startActivity(intent)
//        }
    }

    fun setview(kay: String, editText: EditText) {
        var msg: String = PreferencesUtil.get(this@MainActivity, kay, "").toString()
        if (!TextUtils.isEmpty(msg)) {
            editText.setText(msg)
        }
    }

    fun initWangLuo() {
        networkDialog!!.show()
        if (!TextUtils.isEmpty(MIYAO.toString())) {
            val call = AppClient.retrofit().create(Services::class.java).querypeople(JIQI.toString(), MIYAO.toString(), "4")
            call.enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (null != response.body()) {
                        val user: String = response.body().string()
                        Log.e("1111", user.toString())
                        var ob = JSONObject(user)
                        var status: String = ob.optString("status")
                        if (status == "success") {
                            mViewPager.adapter = myViewPageAdapter
                            mTabLayout.setupWithViewPager(mViewPager)
                            mTabLayout.setTabsFromPagerAdapter(myViewPageAdapter)

                            var obje = ob.optJSONObject("data")
                            //到期时间
                            var expiry_time: String = obje.optString("expiry_time")
                            mian_tv_jiao.text = "到期时间:$expiry_time"
                        } else {
                            Toast.makeText(this@MainActivity, "已超过使用时间", Toast.LENGTH_SHORT).show()
                            PreferencesUtil.remove(this@MainActivity, Contf.MIYAO)
                            var intent = Intent(this@MainActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                    } else {
                        Toast.makeText(this@MainActivity, "已超过使用时间", Toast.LENGTH_SHORT).show()
                        PreferencesUtil.remove(this@MainActivity, Contf.MIYAO)
                        var intent = Intent(this@MainActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    networkDialog!!.dismiss()
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }
            })
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

}


