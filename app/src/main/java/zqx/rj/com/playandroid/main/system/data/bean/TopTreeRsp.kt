package zqx.rj.com.playandroid.main.system.data.bean

/**
 * author：  HyZhan
 * created： 2018/10/22 19:47
 * desc：    体系下 第一层级数据
 */
data class TopTreeRsp(
        val children: List<SecondTreeRsp>,
        val name: String
)