package zqx.rj.com.playandroid.other.bean

import com.zhan.mvvm.bean.KResponse

/**
 * author：  HyZhan
 * created： 2018/10/11 18:29
 * desc：    返回数据 基类
 */
data class BaseResponse<T>(var data: T,
                           var errorCode: Int = -1,
                           var errorMsg: String = "") : KResponse<T> {

    override fun isSuccess(): Boolean = this.errorCode == 0

    override fun getKData(): T = data

    override fun getKMessage(): String = errorMsg
}