package zqx.rj.com.playandroid.system.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.article.vm.ArticleViewModel
import zqx.rj.com.playandroid.system.data.bean.TopTreeRsp
import zqx.rj.com.playandroid.system.data.bean.TreeArticleRsp
import zqx.rj.com.playandroid.system.data.repository.SystemRepository

/**
 * author：  HyZhan
 * created： 2018/10/22 19:33
 * desc：    TODO
 */
class SystemViewModel(application: Application) : ArticleViewModel<SystemRepository>(application) {

    var mTreeData: MutableLiveData<BaseResponse<List<TopTreeRsp>>> = MutableLiveData()
    var mTreeArticleData: MutableLiveData<BaseResponse<TreeArticleRsp>> = MutableLiveData()

    fun getTree() {
        mRepository.getTree(mTreeData)
    }

    fun getArticle(cid: Int, page: Int) {
        mRepository.getArticle(page, cid, mTreeArticleData)
    }
}