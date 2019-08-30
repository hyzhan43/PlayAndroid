package zqx.rj.com.playandroid.main.navigation.vm

import androidx.lifecycle.MutableLiveData
import com.zhan.mvvm.mvvm.BaseViewModel
import zqx.rj.com.playandroid.main.navigation.data.bean.NavigationCategoryRsp
import zqx.rj.com.playandroid.main.navigation.data.repository.NavigationRepository

/**
 * author：  HyZhan
 * created： 2018/10/21 18:51
 * desc：    TODO
 */
class NavigationViewModel : BaseViewModel<NavigationRepository>() {

    val categoryData = MutableLiveData<List<NavigationCategoryRsp>>()

    fun getCategory() {
        launchUI({
            repository.getCategory().execute({ categoryData.value = it })
        })
    }

}