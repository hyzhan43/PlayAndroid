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

    fun updateTodo(id: Int, title: String, time: String, status: Int, type: Int,
                   content: String, priority: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {

        apiService.updateTodo(id, title, time, status, type, content, priority)
                .execute(BaseObserver(liveData, loadState, this))
    }

    fun finishTodo(id: Int, status: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        apiService.finishTodo(id, status)
                .execute(BaseObserver(liveData, loadState, this))
    }

    fun deleteTodo(id: Int, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        apiService.deleteTodo(id).execute(BaseObserver(liveData, loadState, this))
    }

    fun saveTodo(title: String, time: String, type: Int, content: String, liveData: MutableLiveData<BaseResponse<EmptyRsp>>) {
        apiService.saveTodo(title, time, type, content)
                .execute(BaseObserver(liveData, loadState, this))
    }
}