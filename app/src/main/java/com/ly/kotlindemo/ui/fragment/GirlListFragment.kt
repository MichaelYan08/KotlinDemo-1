package com.ly.kotlindemo.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.ly.kotlindemo.R
import com.ly.kotlindemo.adapter.GirlAdapter
import com.ly.kotlindemo.net.ApiServices
import com.ly.kotlindemo.ui.activity.SeeBeautyActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Shinelon on 2017/6/23.
 */

class GirlListFragment : Fragment() {

    var pagerNo: Int = 1
    var xRlvList: XRecyclerView? = null
    private var girlAdapter: GirlAdapter? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        girlAdapter = GirlAdapter(activity)
        xRlvList = view?.findViewById<XRecyclerView>(R.id.xrlv_list)
        xRlvList?.layoutManager = GridLayoutManager(activity, 2)
        xRlvList?.adapter = girlAdapter
        getData(pagerNo)
        xRlvList?.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                pagerNo = 1
                getData(pagerNo)
            }

            override fun onLoadMore() {
                getData(pagerNo)
            }
        })
        girlAdapter?.setOnItemClickListener(object : GirlAdapter.ItemClickListener {
            override fun onItemClickListener(url: String) {
                Log.e("------------------", url)
                val intent = Intent()
                val bun = Bundle()
                bun.putString("url", url)
                //获取intent对象
                intent.setClass(activity, SeeBeautyActivity::class.java)
                // 获取class是使用::反射
                intent.putExtra("extra", bun)
                startActivity(intent)
            }
        })
    }


    fun getData(page: Int) {
        ApiServices.SearchRepository
                .SearchRepositoryProvider
                .provideSearchRepository().searchUsers("福利", 10, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    if (page == 1) {
                        girlAdapter?.setData(result.results)
                    } else {
                        girlAdapter?.addData(result.results)
                    }
                    pagerNo++
                    xRlvList?.loadMoreComplete()
                    xRlvList?.refreshComplete()
                    Log.e("Result", "There are ${result.results.toString()} Java developers in Lagos")
                }, { error ->
                    Log.e("Result", "There are ${error.message} Java developers in Lagos")
                    xRlvList?.loadMoreComplete()
                    xRlvList?.refreshComplete()
                    error.printStackTrace()
                })

    }

    companion object {
        fun newInstance(): GirlListFragment {
            val args = Bundle()
            val fragment = GirlListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
