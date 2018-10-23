package zqx.rj.com.playandroid.mine.view.fragment

import android.util.Log
import android.view.View
import com.kingja.loadsir.callback.SuccessCallback
import kotlinx.android.synthetic.main.common_icon_bar.view.*
import kotlinx.android.synthetic.main.fragment_mine.*
import org.jetbrains.anko.support.v4.toast
import zqx.rj.com.mvvm.base.LifecycleFragment
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.data.callback.LoginSucListener
import zqx.rj.com.playandroid.account.data.context.LoginContext
import zqx.rj.com.playandroid.account.view.LoginActivity
import zqx.rj.com.playandroid.mine.vm.MineViewModel

/**
 * author：  HyZhan
 * created： 2018/10/13 13:51
 * desc：    TODO
 */
class MineFragment : LifecycleFragment<MineViewModel>(),
        View.OnClickListener, LoginSucListener {

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun initView() {
        super.initView()

        mCollectBar.mIvIcon.setImageResource(R.drawable.ic_collect)
        mCollectBar.mTvName.text = getString(R.string.mine_collect)
        mCollectBar.setOnClickListener(this)

        mAboutAuthor.mIvIcon.setImageResource(R.drawable.ic_about)
        mAboutAuthor.mTvName.text = getString(R.string.mine_about)
        mAboutAuthor.setOnClickListener(this)

        mSettingBar.mIvIcon.setImageResource(R.drawable.ic_setting)
        mSettingBar.mTvName.text = getString(R.string.mine_setting)
        mSettingBar.setOnClickListener(this)

        mLogoutBar.mIvIcon.setImageResource(R.drawable.ic_logout)
        mLogoutBar.mTvName.text = getString(R.string.mine_logout)
        mLogoutBar.setOnClickListener(this)

        mCivIcon.setOnClickListener(this)
        mTvName.setOnClickListener(this)
    }

    override fun dataObserver() {
        loadService.showCallback(SuccessCallback::class.java)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mTvName, R.id.mCivIcon -> {
                // 设置 登录成功 监听
                LoginActivity.listener = this
                LoginContext.instance.login(activity)
            }
            R.id.mCollectBar -> {
                toast(getString(R.string.mine_collect))
            }
            R.id.mAboutAuthor -> {
                toast(getString(R.string.mine_about))
            }
            R.id.mSettingBar -> {
                toast(getString(R.string.mine_setting))
            }

            R.id.mLogoutBar -> {
                mViewModel.logout()
            }
        }
    }

    override fun success(collectIds: List<Int>, username: String) {
        mTvName.text = username
    }
}