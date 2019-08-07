package zqx.rj.com.playandroid.mine.todo.data.repository

import com.zhan.mvvm.mvvm.BaseRepository
import zqx.rj.com.playandroid.other.bean.BaseResponse
import zqx.rj.com.playandroid.other.bean.EmptyRsp
import zqx.rj.com.playandroid.other.api.ServiceFactory.apiService
import zqx.rj.com.playandroid.mine.todo.data.bean.PageRsp
import zqx.rj.com.playandroid.mine.todo.data.bean.TodoRsp

/**
 * author：  HyZhan
 * create：  2019/1/2 17:30
 * desc：    TODO
 */
class TodoRepository : BaseRepository() {

    suspend fun getTodoList(page: Int, status: Int, type: Int, priority: Int, orderby: Int): BaseResponse<PageRsp<TodoRsp>> {
        return launchIO { apiService.getTodoListAsync(page, status, type, priority, orderby).await() }
    }

    suspend fun updateTodo(id: Int, title: String, time: String, status: Int, type: Int,
                           content: String, priority: Int): BaseResponse<EmptyRsp> {
        return launchIO { apiService.updateTodoAsync(id, title, time, status, type, content, priority).await() }
    }

    suspend fun finishTodo(id: Int, status: Int): BaseResponse<EmptyRsp> {
        return launchIO { apiService.finishTodoAsync(id, status).await() }
    }

    suspend fun deleteTodo(id: Int): BaseResponse<EmptyRsp> {
        return launchIO { apiService.deleteTodoAsync(id).await() }
    }

    suspend fun saveTodo(title: String, time: String, type: Int, content: String): BaseResponse<EmptyRsp> {
        return launchIO { apiService.saveTodoAsync(title, time, type, content).await() }
    }
}