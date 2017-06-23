package com.ly.kotlindemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ly.kotlindemo.R
import com.ly.kotlindemo.adapter.GirlAdapter.GirlHolder
import com.ly.kotlindemo.model.GankResults
import java.util.*

/**
 * Created by Shinelon on 2017/6/23.
 */
class GirlAdapter(context: Context) : RecyclerView.Adapter<GirlHolder>() {
    private var data = ArrayList<GankResults.Item>()
    private var context: Context = context
    private var onItemClickListener: ItemClickListener? = null
    fun setOnItemClickListener(onItemClickListener: ItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun setData(data: List<GankResults.Item>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: List<GankResults.Item>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: GirlHolder?, position: Int) {
        Glide.with(context).load(data[position].url).into(holder?.ivGril)
        holder?.itemView?.setOnClickListener {
            onItemClickListener?.onItemClickListener(data[position].url)
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GirlHolder {
        return GirlHolder(LayoutInflater.from(context).inflate(R.layout.adapter_gril, parent, false))
    }

    inner class GirlHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var ivGril: ImageView = itemView.findViewById(R.id.iv_girl)
    }

    interface ItemClickListener {
        fun onItemClickListener(url:String)
    }
}