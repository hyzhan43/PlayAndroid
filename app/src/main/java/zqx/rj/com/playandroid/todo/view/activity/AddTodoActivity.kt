package zqx.rj.com.playandroid.todo.view.activity

import android.arch.lifecycle.Observer
import android.support.v4.content.ContextCompat
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
import zqx.rj.com.mvvm.common.format
import zqx.rj.com.mvvm.common.str
import zqx.rj.com.mvvm.common.toColor
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

    override fun getLayoutId(): Int = R.layout.activity_add_todo

    override fun initView() {
        super.initView()

        setToolBar(toolbar, getString(R.string.add_todo))

        // 设置今天日期
        mTvTime.text = Date().format()

        initTimePick()
        mLlDate.setOnClickListener { mTimeView.show() }

        initTypePick()
        mLlType.setOnClickListener { mTypeView.show() }

        // 保存 button
        mBtnSave.setOnClickListener { saveTodo() }

        showSuccess()
    }

    /**
     *  发起保存请求
     */
    private fun saveTodo() {
        showLoading()
        mViewModel.saveTodo(mEtTitle.str(),
                mTvTime.str(),
                getType(mTvType.str()),
                mEtContent.str())
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
            showSuccess()
            toast(getString(R.string.save_suc))
            // 通知 TodoFragment 刷新数据
            TodoContext.notifyTodoRefresh()
            finish()
        })
    }

    private fun getType(type: String): Int {
        return when (type) {
            "全部" -> Constant.ALL
            "工作" -> Constant.WORK
            "学习" -> Constant.STUDY
            "生活" -> Constant.LIFE
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