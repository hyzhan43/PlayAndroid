package zqx.rj.com.playandroid.mine.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kingja.loadsir.callback.SuccessCallback
import zqx.rj.com.mvvm.base.BaseFragment
import zqx.rj.com.playandroid.R

/**
 * author：  HyZhan
 * created： 2018/10/13 13:51
 * desc：    TODO
 */
class MineFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {
        super.initView()
        loadService.showCallback(SuccessCallback::class.java)
    }
}