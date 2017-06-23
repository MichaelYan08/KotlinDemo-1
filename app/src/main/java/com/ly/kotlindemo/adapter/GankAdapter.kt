package com.ly.kotlindemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ly.kotlindemo.R
import com.ly.kotlindemo.model.GankResults
import java.util.*

/**
 * Created by Shinelon on 2017/6/23.
 */
class GankAdapter(context: Context) : RecyclerView.Adapter<GankAdapter.GankHolder>() {
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

    override fun onBindViewHolder(holder: GankHolder?, position: Int) {
        holder?.tv_item?.text = data[position].desc
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GankHolder {
        return GankHolder(LayoutInflater.from(context).inflate(R.layout.adapter_gank, parent, false))
    }

    inner class GankHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tv_item: TextView = itemView.findViewById(R.id.tv_item)
    }
}