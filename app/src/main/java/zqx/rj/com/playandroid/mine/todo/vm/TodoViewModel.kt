package zqx.rj.com.playandroid.mine.todo.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.mvvm.BaseViewModel
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.other.bean.EmptyRsp
import zqx.rj.com.playandroid.mine.todo.data.bean.PageRsp
import zqx.rj.com.playandroid.mine.todo.data.bean.TodoRsp
import zqx.rj.com.playandroid.mine.todo.data.repository.TodoRepository

/**
 * author：  HyZhan
 * create：  2019/1/2 17:29
 * desc：    TODO
 */
class TodoViewModel : BaseViewModel<TodoRepository>() {

    val todoData = MutableLiveData<PageRsp<TodoRsp>>()
    val finishTodoData = MutableLiveData<EmptyRsp>()
    val deleteTodoData = MutableLiveData<EmptyRsp>()
    val saveTodoData = MutableLiveData<EmptyRsp>()
    val updateTodoData = MutableLiveData<EmptyRsp>()

    /**
     *  status 状态， 1完成；0未完成; 默认全部展示 -1；
     *  type   工作1；生活2；娱乐3； 默认全部展示  0;
     *  priority 创建时传入的优先级；默认全部展示  0;
     *  orderBy 1:完成日期顺序；2.完成日期逆序；3.创建日期顺序；4.创建日期逆序(默认)；
     */
    fun getTodoList(
        page: Int = 1,
        status: Int = -1,
        type: Int = 0,
        priority: Int = 0,
        orderBy: Int = 4
    ) {
        launchUI({
            repository.getTodoList(page, status, type, priority, orderBy).execute({
                todoData.value = it
            })
        })
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
    fun updateTodo(
        id: Int,
        title: String,
        time: String,
        status: Int,
        type: Int,
        content: String,
        priority: Int
    ) {

        /**
         *  id: 拼接在链接上，为唯一标识，列表数据返回时，每个todo 都会有个id标识 （必须）
         *  title: 更新标题 （必须）
         *  content: 新增详情（必须）
         *  date: 2018-08-01（必须）
         */

        if (title.isBlank()) {
            showToast(R.string.title_empty)
            return
        }

        if (content.isBlank()) {
            showToast(R.string.content_empty)
            return
        }

        if (time.isBlank()) {
            showToast(R.string.time_empty)
            return
        }

        launchUI({
            repository.updateTodo(id, title, time, status, type, content, priority).execute({
                updateTodoData.value = it
            })
        })
    }

    fun finishTodo(id: Int, status: Int) {
        launchUI({
            repository.finishTodo(id, status).execute({
                finishTodoData.value = it
            })
        })
    }

    fun deleteTodo(id: Int) {
        launchUI({
            repository.deleteTodo(id).execute({
                deleteTodoData.value = it
            })
        })
    }

    fun saveTodo(title: String, time: String, type: Int, content: String) {
        if (title.isBlank()) {
            showToast(R.string.title_empty)
            return
        }

        launchUI({
            repository.saveTodo(title, time, type, content).execute({
                saveTodoData.value = it
            })
        })
    }
}