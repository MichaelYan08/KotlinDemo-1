package com.ly.kotlindemo

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.telephony.TelephonyManager
import android.util.Log
import com.ly.kotlindemo.adapter.MainAdapter
import com.ly.kotlindemo.ui.fragment.GankListFragment
import com.ly.kotlindemo.ui.fragment.GirlListFragment
import com.ly.kotlindemo.ui.fragment.HomeListFragment
import net.youmi.android.AdManager
import net.youmi.android.nm.sp.SplashViewSettings

class MainActivity : AppCompatActivity() {
    /**
     * 碎片列表
     */
    val fragmentList = arrayListOf(
            HomeListFragment.newInstance(),
            GankListFragment.newInstance(),
            GirlListFragment.newInstance()
    )
    /**
     * 菜单
     */
    val menuList = arrayListOf(
            "首页", "干货", "骚全最爱"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AdManager.getInstance(this).init(conf().appId, conf().appSecret, true)
        val toolBar = findViewById(R.id.toolbar) as Toolbar
        val tabMenu = findViewById(R.id.rab_menu) as TabLayout
        val vpContent = findViewById(R.id.vp_content) as ViewPager
        setSupportActionBar(toolBar)
        vpContent.adapter = MainAdapter(supportFragmentManager, fragmentList, menuList)
        vpContent.offscreenPageLimit = fragmentList.size - 1
        tabMenu.setupWithViewPager(vpContent)
        val tel: TelephonyManager = (getSystemService(TELEPHONY_SERVICE) as TelephonyManager)
        val deviceId = tel.deviceId
        Log.e("-------","deviceId-----"+deviceId)

        val splashViewSettings: SplashViewSettings =SplashViewSettings()
        splashViewSettings.isAutoJumpToTargetWhenShowFailed=true
        splashViewSettings.targetClass=MainActivity::class.java

    }
}
