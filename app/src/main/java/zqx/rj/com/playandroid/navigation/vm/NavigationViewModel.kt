package zqx.rj.com.playandroid.navigation.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.mvvm.BaseViewModel
import zqx.rj.com.playandroid.navigation.data.bean.NaviCategoryRsp
import zqx.rj.com.playandroid.navigation.data.repository.NavigationRepository

/**
 * author：  HyZhan
 * created： 2018/10/21 18:51
 * desc：    TODO
 */
class NavigationViewModel : BaseViewModel<NavigationRepository>() {

    val category = MutableLiveData<List<NaviCategoryRsp>>()

    fun getCategory() {
        launchUI({
            repository.getCategory().execute({ category.value = it })
        })
    }

}