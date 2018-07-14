package com.example.fucai4

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.fucai4.utlis.Contf
import kotlinx.android.synthetic.main.act_login.*
import android.telephony.TelephonyManager
import android.os.Handler
import android.os.Message
import android.support.v7.app.AlertDialog
import android.text.TextUtils

import android.widget.Toast
import com.example.fucai4.api.AppClient
import com.example.fucai4.api.Services
import com.example.fucai4.utlis.DataUtlis
import com.example.fucai4.utlis.PreferencesUtil
import com.example.fucai4.view.NetworkDialog
import com.tbruyelle.rxpermissions2.RxPermissions
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


/**
 *
 * Created by 42224 on 2018/4/18.
 */

class LoginActivity : Activity() {
    var imei: String? = null
    //剪切板管理工具类
    private var mClipboardManager: ClipboardManager? = null
    var MIYAO: String? = null

    var networkDialog: NetworkDialog? = null

    var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)
        MIYAO = PreferencesUtil.get(this@LoginActivity, Contf.MIYAO, "").toString()
        if (!TextUtils.isEmpty(MIYAO)) {
            var intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        networkDialog = NetworkDialog(this@LoginActivity)
        but_lonin.setOnClickListener { v: View? ->
            initWangLuo()
        }
        but_fuzhi.setOnClickListener { v: View? ->
            if (!but_fuzhi.getText().toString().equals("")) {
                mClipboardManager!!.setPrimaryClip(ClipData.newPlainText(null, imei))
                Toast.makeText(this@LoginActivity, "复制成功", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@LoginActivity, "机器码不能为空", Toast.LENGTH_SHORT).show()
            }
        }
        val permission = Manifest.permission.READ_PHONE_STATE
        dialog = AlertDialog.Builder(this@LoginActivity).setTitle("申请权限")
                .setMessage("申请读取手机机器码权限")
                .setNegativeButton("取消", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@LoginActivity, "没有获取权限,功能无法使用", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                })
                .setPositiveButton("确定", DialogInterface.OnClickListener { dialog, which ->
                    requestEachRxPermission(permission)
                    dialog.dismiss()
                })
                .create()
        dialog!!.setCanceledOnTouchOutside(false)
        requestEachRxPermission(permission)
    }

    private fun requestEachRxPermission(vararg permissions: String) {
        val rxPermissions = RxPermissions(this)
        rxPermissions.requestEach(*permissions).subscribe { permission ->
            if (permission.granted) {
                Toast.makeText(this@LoginActivity, "已获取权限", Toast.LENGTH_SHORT).show()
                handler.sendEmptyMessage(1)
            } else if (permission.shouldShowRequestPermissionRationale) {
                //拒绝权限请求
                dialog!!.show()
                Toast.makeText(this@LoginActivity, "已拒绝权限" + permission.name, Toast.LENGTH_SHORT).show()
            } else {
                // 拒绝权限请求,并不再询问
                // 需要进入设置界面去设置权限
                Toast.makeText(this@LoginActivity, "系统已拒绝权限, 请在手机系统手动设置给APP权限，否则无法使用", Toast.LENGTH_SHORT).show()
                dialog!!.show()
            }
        }
    }

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    mClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
                    val tm = this@LoginActivity.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                    if (!TextUtils.isEmpty(tm.getDeviceId())) {
                        imei = tm.getDeviceId()
                    } else {
                        //android.provider.Settings;
//                        imei = Settings.Secure.getString(this@LoginActivity.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID)
                        imei = DataUtlis.DangData()
                    }
                    login_ed_jiqi.setText(imei)
                }
                2 -> {

                }
            }
        }
    }


    fun initWangLuo() {
        var et_miyao = lonin_et_my.text
        var et_jiqi = login_ed_jiqi.text
        if (!TextUtils.isEmpty(et_miyao)) {
            if (et_miyao.length == 10) {
                networkDialog!!.show()
                val call = AppClient.retrofit().create(Services::class.java).querypeople(et_jiqi.toString(), et_miyao.toString(), "4")
                call.enqueue(object : retrofit2.Callback<ResponseBody> {

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (null != response.body()) {
                            val user: String? = response.body().string()
                            Log.e("1111", user.toString())
                            var ob = JSONObject(user)
                            var status: String = ob.optString("status")
                            if (status.equals("success")) {
                                Toast.makeText(this@LoginActivity, "验证成功，可以使用！", Toast.LENGTH_SHORT).show()
                                PreferencesUtil.put(this@LoginActivity, Contf.JIQI, et_jiqi.toString())
                                PreferencesUtil.put(this@LoginActivity, Contf.MIYAO, et_miyao.toString())
                                var intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@LoginActivity, "密钥或机器码不对应", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@LoginActivity, "密钥或机器码不对应", Toast.LENGTH_SHORT).show()
                        }
                        networkDialog!!.dismiss()
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "密钥或机器码不对应", Toast.LENGTH_SHORT).show()
                        networkDialog!!.dismiss()
                    }
                })

            } else {
                Toast.makeText(this@LoginActivity, "密钥长度不正确", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this@LoginActivity, "密钥不能为空", Toast.LENGTH_SHORT).show()
        }
    }
}

