package com.ly.kotlindemo.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.ly.kotlindemo.R
import com.ly.kotlindemo.adapter.HomeAdapter
import com.ly.kotlindemo.net.ApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Shinelon on 2017/6/23.
 */

class GankListFragment : Fragment() {
    var pagerNo: Int = 1
    var xRlvList: XRecyclerView? = null
    private var gankAdapter: HomeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gankAdapter = HomeAdapter(activity)
        xRlvList = view?.findViewById<XRecyclerView>(R.id.xrlv_list)
        xRlvList?.layoutManager = LinearLayoutManager(activity)
        xRlvList?.adapter = gankAdapter
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
    }


    fun getData(page: Int) {
        ApiServices.SearchRepository
                .SearchRepositoryProvider
                .provideSearchRepository().searchUsers("Android", 10, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    result ->
                    if (page == 1) {
                        gankAdapter?.setData(result.results)
                    } else {
                        gankAdapter?.addData(result.results)
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
        fun newInstance(): GankListFragment {
            val args = Bundle()
            val fragment = GankListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
