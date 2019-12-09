package zqx.rj.com.playandroid

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import zqx.rj.com.playandroid.BaseTest.onClick
import zqx.rj.com.playandroid.BaseTest.onEditView
import zqx.rj.com.playandroid.BaseTest.onToast
import zqx.rj.com.playandroid.account.view.RegisterActivity

/**
 *  author: HyJame
 *  date:   2019-12-09
 *  desc:   注册页面——注册流程测试
 */
@RunWith(AndroidJUnit4::class)
class RegisterProcessTest {

    private val registerAccount = "2311120978"
    private val registerPassword = "123456"

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(RegisterActivity::class.java)

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
    fun test_empty_account() {
        onClick(R.id.mBtnRegister)
        onToast(activityTestRule.activity, R.string.account_empty)
    }

    @Test
    fun test_empty_password() {
        onEditView(R.id.mTieAccount, registerAccount)
        onClick(R.id.mBtnRegister)
        onToast(activityTestRule.activity, R.string.password_empty)
    }

    @Test
    fun test_password_not_same() {

        onEditView(R.id.mTieAccount, registerAccount)
        onEditView(R.id.mTiePassword, registerPassword)
        onEditView(R.id.mTiePasswordAg, "111")
        onClick(R.id.mBtnRegister)
        onToast(activityTestRule.activity, R.string.repassword_error)
    }

    @Test
    fun test_register_success() {
        onEditView(R.id.mTieAccount, registerAccount)
        onEditView(R.id.mTiePassword, registerPassword)
        onEditView(R.id.mTiePasswordAg, registerPassword)

        IdlingRegistry.getInstance().register(idlingResource)
        onClick(R.id.mBtnRegister)

        assertTrue(activityTestRule.activity.isFinishing)
    }
}