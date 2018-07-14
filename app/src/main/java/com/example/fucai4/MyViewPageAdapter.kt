package com.example.fucai4

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by 42224 on 2018/7/5.
 */
class MyViewPageAdapter : FragmentPagerAdapter {
    private var titleList: ArrayList<String>
    private var fragmentList: ArrayList<Fragment>

    constructor(fm: FragmentManager, titleList: ArrayList<String>, fragmentList: ArrayList<Fragment>) : super(fm) {

        this.titleList = titleList
        this.fragmentList = fragmentList
    }


    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList.get(position)
    }


}