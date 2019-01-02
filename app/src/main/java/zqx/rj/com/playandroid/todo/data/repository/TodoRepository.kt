package zqx.rj.com.playandroid.todo.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.execute
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.common.net.ApiRepository
import zqx.rj.com.playandroid.common.net.BaseObserver
import zqx.rj.com.playandroid.todo.data.bean.PageRsp
import zqx.rj.com.playandroid.todo.data.bean.TodoRsp

/**
 * author：  HyZhan
 * create：  2019/1/2 17:30
 * desc：    TODO
 */
class TodoRepository(val loadState: MutableLiveData<State>) : ApiRepository() {

    fun getTodoList(page: Int, liveData: MutableLiveData<BaseResponse<PageRsp<TodoRsp>>>) {
        apiService.getTodoList(page)
                .execute(BaseObserver(liveData, loadState, this))
    }
}