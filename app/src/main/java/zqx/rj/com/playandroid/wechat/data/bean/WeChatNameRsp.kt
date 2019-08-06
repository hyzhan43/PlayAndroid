package zqx.rj.com.playandroid.wechat.data.bean

/**
 * author：  HyZhan
 * created： 2018/11/2 16:48
 * desc：    微信公众号列表
 */
data class WeChatNameRsp(
        val courseId: Int,
        val name: String,
        val id: Int,
        val order: Int,
        val parentChapterId: Int,
        val userControlSetTop: Boolean
)