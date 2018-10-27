package zqx.rj.com.mvvm.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * author：  HyZhan
 * created： 2018/10/14 18:35
 * desc：    TODO
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var loadService: LoadService<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(getLayoutId(), null)

        loadService = LoadSir.getDefault().register(rootView) { reLoad() }
        return loadService.loadLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        initData()
    }

    abstract fun initView()

    open fun initData() {}

    // 重新加载
    open fun reLoad() {
        initData()
    }

    abstract fun getLayoutId(): Int
}