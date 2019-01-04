package zqx.rj.com.playandroid.todo.vm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.base.BaseViewModel
import zqx.rj.com.mvvm.common.State
import zqx.rj.com.mvvm.common.constant.StateType
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.response.EmptyRsp
import zqx.rj.com.playandroid.R
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
    var mFinishTodoData: MutableLiveData<BaseResponse<EmptyRsp>> = MutableLiveData()
    var mDeleteTodoData: MutableLiveData<BaseResponse<EmptyRsp>> = MutableLiveData()
    var mSaveTodoData: MutableLiveData<BaseResponse<EmptyRsp>> = MutableLiveData()

    /**
     *  status 状态， 1完成；0未完成; 默认全部展示 -1；
     *  type   工作1；生活2；娱乐3； 默认全部展示  0;
     *  priority 创建时传入的优先级；默认全部展示  0;
     *  orderby 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；
     */
    fun getTodoList(page: Int = 1, status: Int = -1, type: Int = 0, priority: Int = 0,
                    orderby: Int = 4) {

        mRepository.getTodoList(page, status, type, priority, orderby, mTodoData)
    }

    /**
     *   id: 拼接在链接上，为唯一标识，列表数据返回时，每个todo 都会有个id标识 （必须）
     *   title: 更新标题 （必须）
     *   content: 新增详情（必须）
     *   date: 2018-08-01（必须）
     *   status: 0 // 0为未完成，1为完成
     *   type: ；
     *   priority:
     */
    fun updateTodo(id: Int, title: String, content: String, status: Int,
                   type: Int, priority: Int) {

        mRepository.updateTodo()
    }

    fun finishTodo(id: Int, status: Int) {
        mRepository.finishTodo(id, status, mFinishTodoData)
    }

    fun deleteTodo(id: Int) {
        mRepository.deleteTodo(id, mDeleteTodoData)
    }

    fun saveTodo(title: String, time: String, type: Int, content: String) {
        if (title.isBlank()) {
            loadState.postValue(State(StateType.TIPS, tip = R.string.title_empty))
            return
        }

        mRepository.saveTodo(title, time, type, content, mSaveTodoData)
    }
}