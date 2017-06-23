package com.ly.kotlindemo.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

/**
 * Created by Shinelon on 2017/6/22.
 */

class MainAdapter(fm: FragmentManager, fragmentList: ArrayList<Fragment>, menuList: ArrayList<String>) : FragmentPagerAdapter(fm) {
    private var fragmentList = ArrayList<Fragment>()
    private var menuList = ArrayList<String>()

    init {
        this.fragmentList = fragmentList
        this.menuList = menuList
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return menuList[position]
    }
}
