package zqx.rj.com.playandroid.todo.view.fragment

import zqx.rj.com.mvvm.base.BaseFragment
import zqx.rj.com.playandroid.R

/**
 * author：  HyZhan
 * create：  2019/1/3 16:25
 * desc：    TODO
 */
class SettingFragment:BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_setting

    override fun initView() {
        loadService.showSuccess()
    }
}