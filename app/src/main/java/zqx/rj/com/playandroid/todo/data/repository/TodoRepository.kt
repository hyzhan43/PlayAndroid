package zqx.rj.com.playandroid.todo.data.repository

import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.execute
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.response.EmptyRsp
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

    fun getTodoList(page: Int, status: Int, type: Int, priority: Int, orderby: Int,
                    liveData: MutableLiveData<BaseResponse<PageRsp<TodoRsp>>>) {

        apiService.getTodoList(page, status, type, priority, orderby)
                .execute(BaseObserver(liveData, loadState, this))
    }

    fun updateTodo() {

    }

    fun finishTodo(id: Int, status: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        apiService.finishTodo(id, status)
                .execute(BaseObserver(liveData, loadState, this))
    }

    fun deleteTodo(id: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        apiService.deleteTodo(id).execute(BaseObserver(liveData, loadState, this))
    }
}