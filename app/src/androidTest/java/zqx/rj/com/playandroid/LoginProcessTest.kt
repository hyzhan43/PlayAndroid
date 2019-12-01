package zqx.rj.com.playandroid

import android.app.Activity
import android.content.Intent
import androidx.test.espresso.Espresso.registerIdlingResources
import androidx.test.espresso.Espresso.unregisterIdlingResources
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.zhan.ktwing.ext.logd
import org.junit.*
import org.junit.Assert.assertTrue
import org.junit.experimental.theories.Theories
import org.junit.runner.RunWith
import zqx.rj.com.playandroid.BaseTest.onClick
import zqx.rj.com.playandroid.BaseTest.onEditView
import zqx.rj.com.playandroid.BaseTest.onToast
import zqx.rj.com.playandroid.account.view.LoginActivity
import zqx.rj.com.playandroid.main.MainActivity
import zqx.rj.com.playandroid.other.config.RetrofitConfig

/**
 *  author: HyJame
 *  date:   2019-11-28
 *  desc:   TODO
 */
@RunWith(AndroidJUnit4::class)
class LoginProcessTest {

    private val account: String = "1142948328"
    private val password: String = "123456"

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(LoginActivity::class.java)

    fun ActivityTestRule<out Activity>.isFinished(): Boolean = activity.isFinishing

    private lateinit var idlingResource: IdlingResource

    @Before
    fun setUp() {
        idlingResource = activityTestRule.activity.mIdlingResource
    }


    @After
    fun release() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun test_empty_account_and_empty_password_login() {

        onEditView(R.id.mTieAccount, "")
        onEditView(R.id.mTiePassword, "")

        onClick(R.id.mBtnLogin)

        onToast(activityTestRule.activity, R.string.account_empty)
    }

    @Test
    fun test_empty_password_login() {
        onEditView(R.id.mTieAccount, account)
        onEditView(R.id.mTiePassword, "")

        onClick(R.id.mBtnLogin)

        onToast(activityTestRule.activity, R.string.password_empty)
    }

    @Test
    fun test_error_account_or_password() {
        onEditView(R.id.mTieAccount, account)
        onEditView(R.id.mTiePassword, "123")

        onClick(R.id.mBtnLogin)

        IdlingRegistry.getInstance().register(idlingResource)
        onToast(activityTestRule.activity, "账号密码不匹配！")
    }

    @Test
    fun test_login_success_by_myself() {
        onEditView(R.id.mTieAccount, account)
        onEditView(R.id.mTiePassword, password)

        IdlingRegistry.getInstance().register(idlingResource)
        onClick(R.id.mBtnLogin)

        assertTrue(activityTestRule.isFinished())
    }
}