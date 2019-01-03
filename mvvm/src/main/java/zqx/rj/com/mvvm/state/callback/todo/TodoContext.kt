package zqx.rj.com.mvvm.state.callback.todo

/**
 * author：  HyZhan
 * create：  2019/1/3 19:33
 * desc：    TODO
 */
object TodoTypeContext {

    private val listeners = ArrayList<TypeChangeListener>()

    fun addListener(listener: TypeChangeListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: TypeChangeListener) {
        listeners.remove(listener)
    }

    fun notifyTodoTypeChange(type: Int) {
        for (listener in listeners) {
            listener.change(type)
        }
    }
}