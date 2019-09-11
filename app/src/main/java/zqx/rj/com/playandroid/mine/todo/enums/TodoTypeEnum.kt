package zqx.rj.com.playandroid.mine.todo.enums

/**
 *  @author: HyJame
 *  @date:   2019-09-07
 *  @desc:   TODO
 */
enum class TodoTypeEnum(val code: Int, val desc: String) {
    ALL(0, "全部"),
    WORK(1, "工作"),
    STUDY(2, "学习"),
    LIFE(3, "生活")
}