package zqx.rj.com.playandroid.mine.todo.view.activity

import androidx.lifecycle.Observer
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import com.zhan.mvvm.ext.Toasts.toast
import com.zhan.mvvm.ext.getColorRef
import com.zhan.mvvm.ext.str
import com.zhan.mvvm.mvvm.LifecycleActivity
import kotlinx.android.synthetic.main.activity_add_todo.*
import zqx.rj.com.playandroid.other.context.callback.todo.TodoContext
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.other.constant.Const
import zqx.rj.com.playandroid.other.ext.format
import zqx.rj.com.playandroid.other.ext.toHtml
import zqx.rj.com.playandroid.mine.todo.vm.TodoViewModel
import java.util.*
import kotlin.collections.ArrayList


/**
 * author：  HyZhan
 * create：  2019/1/2 11:44
 * desc：    todo添加页面
 */
class AddTodoActivity : LifecycleActivity<TodoViewModel>() {

    private lateinit var mTimeView: TimePickerView
    private lateinit var mTypeView: OptionsPickerView<String>

    // 默认 id 为 -1
    private var id: Int = -1
    // todo状态
    private var status: Int = 0
    // todo级别
    private var priority: Int = 0

    override fun getLayoutId(): Int = R.layout.activity_add_todo

    override fun initView() {
        super.initView()

        // setToolBar(toolbar, getString(R.string.add_todo))

        initTimePick()
        mLlDate.setOnClickListener { mTimeView.show() }

        initTypePick()
        mLlType.setOnClickListener { mTypeView.show() }

        // 保存 button
        mBtnSave.setOnClickListener {
            // 如果有 id 则是更新, 否则是保存新的todo
            if (id == -1) saveTodo() else updateTodo()
        }

    }

    override fun initData() {
        super.initData()

        with(intent) {
            id = getIntExtra("id", -1)
            status = getIntExtra("status", 0)
            priority = getIntExtra("priority", 0)
            val title = (getStringExtra("title") ?: "").toHtml()
            mEtTitle.setText(title)
            mEtTitle.setSelection(title.length)
            // 如果 time 没有，就设置 当前日期
            mTvTime.text = getStringExtra("time") ?: Date().format()
            mTvType.text = getStringType(getIntExtra("type", 0))
            mEtContent.setText((getStringExtra("content") ?: "").toHtml())
        }
    }

    /**
     *  发起保存请求
     */
    private fun saveTodo() {
        showLoading()
        viewModel.saveTodo(mEtTitle.str(),
                mTvTime.str(),
                getIntType(mTvType.str()),
                mEtContent.str())
    }

    /**
     *  发起更新请求
     */
    private fun updateTodo() {
        showLoading()
        viewModel.updateTodo(
                id,
                mEtTitle.str(),
                mTvTime.str(),
                status,
                getIntType(mTvType.str()),
                mEtContent.str(),
                priority)
    }

    private fun initTimePick() {

        //时间选择器
        mTimeView = TimePickerBuilder(this, OnTimeSelectListener { date, _ ->
            mTvTime.text = date.format()
        }).setSubmitColor(getColorRef(R.color.colorPrimaryDark))
                .setCancelColor(getColorRef(R.color.colorPrimaryDark))
                .build()
    }

    private fun initTypePick() {

        val typeList = ArrayList<String>()
        typeList.add(getString(R.string.mixed))
        typeList.add(getString(R.string.work))
        typeList.add(getString(R.string.life))
        typeList.add(getString(R.string.study))

        //条件选择器
        mTypeView = OptionsPickerBuilder(this, OnOptionsSelectListener { options1, _, _, _ ->
            // options1 为 选中索引
            mTvType.text = typeList[options1]
        }).setSubmitColor(getColorRef(R.color.colorPrimaryDark))
                .setCancelColor(getColorRef(R.color.colorPrimaryDark))
                .build<String>()

        // 设置数据源
        mTypeView.setPicker(typeList)
    }

    override fun dataObserver() {
        viewModel.saveTodoData.observe(this, Observer {
            refreshData()
        })

        viewModel.updateTodoData.observe(this, Observer {
            refreshData()
        })
    }

    private fun refreshData() {
        toast(getString(R.string.save_suc))
        // 通知 TodoFragment 刷新数据
        TodoContext.notifyTodoRefresh()
        finish()
    }

    private fun getStringType(type: Int): String {
        return when (type) {
            Const.ALL -> getString(R.string.mixed)
            Const.WORK -> getString(R.string.work)
            Const.STUDY -> getString(R.string.study)
            Const.LIFE -> getString(R.string.life)
            else -> getString(R.string.mixed)
        }
    }

    private fun getIntType(type: String): Int {
        return when (type) {
            getString(R.string.work) -> Const.ALL
            getString(R.string.work) -> Const.WORK
            getString(R.string.study) -> Const.STUDY
            getString(R.string.life) -> Const.LIFE
            else -> Const.ALL
        }
    }

    /**
     *  保存失败，重新保存回调
     */
//    override fun reLoad() {
//        super.reLoad()
//        // 发起保存请求
//        saveTodo()
//    }

    override fun onBackPressed() = finish()
}