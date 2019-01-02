package zqx.rj.com.playandroid.todo.view.activity

import zqx.rj.com.mvvm.base.BaseActivity
import zqx.rj.com.playandroid.R

/**
 * author：  HyZhan
 * create：  2019/1/2 11:44
 * desc：    todo 添加页面
 */
class AddTodoActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_add_todo

    override fun onBackPressed() = finish()
}