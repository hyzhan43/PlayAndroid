package zqx.rj.com.playandroid.system.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.rx.BaseObserver
import zqx.rj.com.mvvm.http.rx.RxSchedulers
import zqx.rj.com.playandroid.net.ApiRepository
import zqx.rj.com.playandroid.system.data.bean.TopTreeRsp
import zqx.rj.com.playandroid.system.data.bean.TreeArticleRsp

/**
 * author：  HyZhan
 * created： 2018/10/22 19:34
 * desc：    TODO
 */
class SystemRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun getTree(liveData: MutableLiveData<BaseResponse<List<TopTreeRsp>>>) {
        addSubscribe(apiService.getTree()
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<List<TopTreeRsp>>>(liveData, loadState) {}))
    }

    fun getArticle(page: Int, cid: Int, liveData: MutableLiveData<BaseResponse<TreeArticleRsp>>) {
        addSubscribe(apiService.getTreeArticle(page, cid)
                .compose(RxSchedulers.ioToMain())
                .subscribe(object : BaseObserver<BaseResponse<TreeArticleRsp>>(liveData, loadState) {}))
    }
}