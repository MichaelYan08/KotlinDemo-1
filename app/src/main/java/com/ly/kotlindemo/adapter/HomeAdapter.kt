package com.ly.kotlindemo.adapter

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ly.kotlindemo.R
import com.ly.kotlindemo.model.GankResults
import java.util.*

/**
 * Created by Shinelon on 2017/6/23.
 */
class HomeAdapter(context: Context) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    private var data = ArrayList<GankResults.Item>()
    private var context: Context = context

    fun setData(data: List<GankResults.Item>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    fun addData(data: List<GankResults.Item>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: HomeHolder?, position: Int) {
        var dataItem: GankResults.Item = data[position]
        val type: String = dataItem.type
        when (type) {
            "休息视频" -> {
                holder?.rlMessage?.visibility = View.VISIBLE
                holder?.ivPart?.visibility = View.GONE
                holder?.ivVedio?.visibility = View.VISIBLE
                holder?.tvItem?.text = dataItem.desc
            }
            "福利" -> {
                holder?.rlMessage?.visibility = View.GONE
                holder?.ivPart?.visibility = View.VISIBLE
                holder?.ivVedio?.visibility = View.GONE
                Glide.with(context).load(dataItem.url).into(holder?.ivPart)
            }
            else -> {
                holder?.rlMessage?.visibility = View.VISIBLE
                holder?.ivPart?.visibility = View.GONE
                holder?.ivVedio?.visibility = View.GONE
                holder?.tvItem?.text = dataItem.desc
            }
        }
        val uri: Uri? = null
        when (dataItem.type) {
            "Android" -> holder?.ivType?.setImageResource(R.mipmap.android_icon)
            "iOS" -> holder?.ivType?.setImageResource(R.mipmap.ios_icon)
            "前端" -> holder?.ivType?.setImageResource(R.mipmap.js_icon)
            "拓展资源" -> holder?.ivType?.setImageResource(R.mipmap.other_icon)
        }
        val author = dataItem.who
        if (author != null) {
            holder?.tvAuthor?.text = author
            holder?.tvAuthor?.setTextColor(Color.parseColor("#87000000"))
        } else {
            holder?.tvAuthor?.text = ""
        }
        holder?.tvTime?.text = dataItem.createdAt
        holder?.tvType?.text = type
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeHolder {
        return HomeHolder(LayoutInflater.from(context).inflate(R.layout.adapter_home, parent, false))
    }


    inner class HomeHolder(item: View) : RecyclerView.ViewHolder(item) {
        internal var ivType: ImageView
        internal var tvType: TextView
        internal var ivAuthor: ImageView
        internal var tvAuthor: TextView
        internal var tvTime: TextView
        internal var rlMessage: RelativeLayout
        internal var ivPart: ImageView
        internal var ivVedio: ImageView
        internal var tvItem: TextView

        init {
            ivType = itemView.findViewById<ImageView>(R.id.iv_type)
            tvType = itemView.findViewById<TextView>(R.id.tv_type)
            ivAuthor = itemView.findViewById<ImageView>(R.id.iv_author)
            tvAuthor = itemView.findViewById<TextView>(R.id.tv_author)
            tvTime = itemView.findViewById<TextView>(R.id.tv_time)
            rlMessage = itemView.findViewById<RelativeLayout>(R.id.rl_message)
            ivPart = itemView.findViewById<ImageView>(R.id.iv_part)
            ivVedio = itemView.findViewById<ImageView>(R.id.iv_vedio)
            tvItem = itemView.findViewById<TextView>(R.id.tv_item)
        }
    }
}