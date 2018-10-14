package zqx.rj.com.mvvm.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import zqx.rj.com.mvvm.common.Util
import zqx.rj.com.mvvm.common.State

/**
 * author：  HyZhan
 * created： 2018/10/11 13:47
 * desc：    TODO
 */
abstract class BaseViewModel<T : BaseRepository>(application: Application)
    : AndroidViewModel(application) {

    val loadState by lazy {
        MutableLiveData<State>()
    }

    val mRepository: T by lazy {
        // 获取 对应 Repository 实例 (有参构造函数)
         (Util.getClass<T>(this))
                 //获取构造函数的构造器, 传入参数 Class
                 .getDeclaredConstructor(MutableLiveData::class.java)
                 // 传入参数
                 .newInstance(loadState)
    }

    override fun onCleared() {
        super.onCleared()
        mRepository.unSubscribe()
    }

}