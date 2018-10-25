package zqx.rj.com.mvvm.state.callback

/**
 * author：  HyZhan
 * created： 2018/10/25 16:05
 * desc：    TODO
 */
object CollectState {

    var listeners = ArrayList<CollectUpdateListener>()

    fun addListener(listener: CollectUpdateListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: CollectUpdateListener) {
        listeners.remove(listener)
    }

    fun notifyCollectState(id: Int){
        for (listener in listeners) {
            listener.updateState(id)
        }
    }
}