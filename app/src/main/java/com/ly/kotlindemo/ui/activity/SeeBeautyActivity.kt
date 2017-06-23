package com.ly.kotlindemo.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ly.kotlindemo.R
import uk.co.senab.photoview.PhotoView

/**
 * Created by Shinelon on 2017/6/23.
 */

class SeeBeautyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bun: Bundle? = intent.getBundleExtra("extra")
        val url: String? = bun?.getString("url")
        setContentView(R.layout.activity_see_beauty)
        val pvSeeBeauty = findViewById(R.id.pv_beauty_pic) as PhotoView
        Glide.with(this).load(url)?.into(pvSeeBeauty)

    }
}
