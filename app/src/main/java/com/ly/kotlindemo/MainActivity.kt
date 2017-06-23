package com.ly.kotlindemo

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.ly.kotlindemo.adapter.MainAdapter
import com.ly.kotlindemo.ui.fragment.GankListFragment
import com.ly.kotlindemo.ui.fragment.GirlListFragment
import com.ly.kotlindemo.ui.fragment.HomeListFragment

class MainActivity : AppCompatActivity() {

    /**
     * 碎片列表
     */
    val fragmentList = arrayListOf<Fragment>(
            HomeListFragment.newInstance(),
            GankListFragment.newInstance(),
            GirlListFragment.newInstance()
    )
    /**
     * 菜单
     */
    val menuList = arrayListOf<String>(
            "首页", "干货", "骚全最爱"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolBar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolBar)
        val tabMenu = findViewById(R.id.rab_menu) as TabLayout
        val vpContent = findViewById(R.id.vp_content) as ViewPager
        vpContent.adapter = MainAdapter(supportFragmentManager, fragmentList, menuList)
        vpContent.offscreenPageLimit=fragmentList.size-1
        tabMenu.setupWithViewPager(vpContent)
    }
}
