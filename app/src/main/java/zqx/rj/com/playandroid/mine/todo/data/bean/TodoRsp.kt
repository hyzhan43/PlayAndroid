package zqx.rj.com.playandroid.mine.todo.data.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * author：  HyZhan
 * create：  2019/1/2 17:36
 * desc：    TODO
 */
@Parcelize
data class TodoRsp(
        val completeDate: String?,
        val content: String?,
        val dateStr: String?,
        val id: Int?,
        var priority: Int?,
        val status: Int?,
        val type: Int?,
        val title: String?
): Parcelable