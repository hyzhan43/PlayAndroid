package zqx.rj.com.playandroid.wechat.data.bean

/**
 * author：  HyZhan
 * created： 2018/11/2 16:48
 * desc：    微信公众号列表
 */
data class WeChatNameRsp(
        var courseId: Int,
        var name: String,
        var id: Int,
        var order: Int,
        var parentChapterId: Int,
        var userControlSetTop: Boolean
)