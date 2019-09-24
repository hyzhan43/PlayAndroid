package zqx.rj.com.playandroid.account.data.bean

/**
 *  @author: HyJame
 *  @date:   2019-09-24
 *  @desc:   TODO
 */
data class UserInfoRsp(
    val coinCount: Int,
    val rank: Int,
    val userId: Int,
    val username: String,
    val icon: String,
    val type: String,
    val collectIds: List<Int>
) {
    constructor(loginRsp: LoginRsp, scoreInfoRsp: ScoreInfoRsp) : this(
        scoreInfoRsp.coinCount,
        scoreInfoRsp.rank,
        scoreInfoRsp.userId,
        scoreInfoRsp.username,
        loginRsp.icon,
        loginRsp.type,
        loginRsp.collectIds
    )
}
