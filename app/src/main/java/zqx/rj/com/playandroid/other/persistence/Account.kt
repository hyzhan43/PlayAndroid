package zqx.rj.com.playandroid.other.persistence

import com.zhan.mvvm.common.Preference
import zqx.rj.com.playandroid.other.constant.Const
import zqx.rj.com.playandroid.other.constant.Key

/**
 * author：  HyZhan
 * create：  2019/8/13
 * desc：    TODO
 */
object Account {

    var username by Preference(Key.USERNAME, Const.NOT_LOGIN_MSG)

    var cookie by Preference(Key.COOKIE, "")
}