package zqx.rj.com.playandroid.todo.view.activity

import android.arch.lifecycle.Observer
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import kotlinx.android.synthetic.main.activity_add_todo.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.base.LifecycleActivity
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.mvvm.ext.format
import zqx.rj.com.mvvm.ext.str
import zqx.rj.com.mvvm.ext.toColor
import zqx.rj.com.mvvm.ext.toHtml
import zqx.rj.com.mvvm.state.callback.todo.TodoContext
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.todo.vm.TodoViewModel
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

        setToolBar(toolbar, getString(R.string.add_todo))

        initTimePick()
        mLlDate.setOnClickListener { mTimeView.show() }

        initTypePick()
        mLlType.setOnClickListener { mTypeView.show() }

        // 保存 button
        mBtnSave.setOnClickListener {
            // 如果有 id 则是更新, 否则是保存新的todo
            if (id == -1) saveTodo() else updateTodo()
        }

        showSuccess()
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
        mViewModel.saveTodo(mEtTitle.str(),
                mTvTime.str(),
                getIntType(mTvType.str()),
                mEtContent.str())
    }

    /**
     *  发起更新请求
     */
    private fun updateTodo() {
        showLoading()
        mViewModel.updateTodo(
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
        }).setSubmitColor(R.color.colorPrimaryDark.toColor(this))
                .setCancelColor(R.color.colorPrimaryDark.toColor(this))
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
        }).setSubmitColor(R.color.colorPrimaryDark.toColor(this))
                .setCancelColor(R.color.colorPrimaryDark.toColor(this))
                .build<String>()

        // 设置数据源
        mTypeView.setPicker(typeList)
    }

    override fun dataObserver() {
        mViewModel.mSaveTodoData.observe(this, Observer {
            refreshData()
        })

        mViewModel.mUpdateTodoData.observe(this, Observer {
            refreshData()
        })
    }

    private fun refreshData() {
        showSuccess()
        toast(getString(R.string.save_suc))
        // 通知 TodoFragment 刷新数据
        TodoContext.notifyTodoRefresh()
        finish()
    }

    private fun getStringType(type: Int): String {
        return when (type) {
            Constant.ALL -> getString(R.string.mixed)
            Constant.WORK -> getString(R.string.work)
            Constant.STUDY -> getString(R.string.study)
            Constant.LIFE -> getString(R.string.life)
            else -> getString(R.string.mixed)
        }
    }

    private fun getIntType(type: String): Int {
        return when (type) {
            getString(R.string.work) -> Constant.ALL
            getString(R.string.work) -> Constant.WORK
            getString(R.string.study) -> Constant.STUDY
            getString(R.string.life) -> Constant.LIFE
            else -> Constant.ALL
        }
    }

    /**
     *  保存失败，重新保存回调
     */
    override fun reLoad() {
        super.reLoad()
        // 发起保存请求
        saveTodo()
    }

    override fun onBackPressed() = finish()
}