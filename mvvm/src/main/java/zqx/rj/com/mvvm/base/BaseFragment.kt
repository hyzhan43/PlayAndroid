package zqx.rj.com.mvvm.base

import android.os.Bundle
import android.support.annotation.IntegerRes
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

    protected val loadService: LoadService<*> by lazy {
        LoadSir.getDefault().register(this) {
            reLoad()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutId(), null)

        initView()
        initData()

        return view
    }

    open fun initView() {}

    open fun initData() {}

    // 重新加载
    open fun reLoad() {}

    abstract fun getLayoutId(): Int
}