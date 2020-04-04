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

    suspend fun getTodoList(page: Int, status: Int, type: Int, priority: Int, orderBy: Int): BaseResponse<PageRsp<TodoRsp>> {
        return apiService.getTodoListAsync(page, status, type, priority, orderBy)
    }

    suspend fun updateTodo(id: Int, title: String, time: String, status: Int, type: Int,
                           content: String, priority: Int): BaseResponse<EmptyRsp> {
        return apiService.updateTodoAsync(id, title, time, status, type, content, priority)
    }

    suspend fun finishTodo(id: Int, status: Int): BaseResponse<EmptyRsp> {
        return  apiService.finishTodoAsync(id, status)
    }

    suspend fun deleteTodo(id: Int): BaseResponse<EmptyRsp> {
        return apiService.deleteTodoAsync(id)
    }

    suspend fun saveTodo(title: String, time: String, type: Int, content: String): BaseResponse<EmptyRsp> {
        return apiService.saveTodoAsync(title, time, type, content)
    }
}