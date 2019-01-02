package zqx.rj.com.playandroid.todo.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.playandroid.todo.data.bean.PageRsp
import zqx.rj.com.playandroid.todo.data.bean.TodoRsp
import zqx.rj.com.playandroid.todo.data.repository.TodoRepository

/**
 * author：  HyZhan
 * create：  2019/1/2 17:29
 * desc：    TODO
 */
class TodoViewModel(application: Application) : BaseViewModel<TodoRepository>(application) {

    var mTodoData: MutableLiveData<BaseResponse<PageRsp<TodoRsp>>> = MutableLiveData()

    fun getTodoList(page: Int) {
        mRepository.getTodoList(page, mTodoData)
    }
}