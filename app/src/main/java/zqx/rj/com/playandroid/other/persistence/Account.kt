package zqx.rj.com.playandroid.other.persistence

import com.zhan.ktwing.common.Preference
import zqx.rj.com.playandroid.other.constant.Const
import zqx.rj.com.playandroid.other.constant.Key

/**
 * author：  HyZhan
 * create：  2019/8/13
 * desc：    TODO
 */
object Account {

    var username by Preference(Key.USERNAME, Const.NOT_LOGIN_MSG)

    var score by Preference(Key.SCORE, 0)

    var rank by Preference(Key.RANK, 0)

    var cookie by Preference(Key.COOKIE, "")
}