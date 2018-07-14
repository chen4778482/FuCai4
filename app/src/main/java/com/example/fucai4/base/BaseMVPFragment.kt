package com.example.fucai4.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fucai4.utlis.Contf
import com.example.fucai4.utlis.PreferencesUtil

/**
 * Created by 42224 on 2018/6/1.
 */
abstract class BaseMVPFragment : Fragment() {
    abstract fun getLayoutId(): Int
    abstract fun findViews(view: View?)
    private fun initListener() {}
    abstract fun setViews()
    abstract fun getData()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater?.inflate(getLayoutId(), container, false)
        //创建presenter

        //初始化控件
        findViews(view)

        initViews()
        return view

    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //初始化控件
        initViews()
    }

    private fun initViews() {
        setViews()
        initListener()
        getData()

    }


}





