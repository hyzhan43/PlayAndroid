package zqx.rj.com.playandroid

import android.content.Intent
import android.view.Gravity
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions.open
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import zqx.rj.com.playandroid.BaseTest.onClick
import zqx.rj.com.playandroid.BaseTest.onEditView
import zqx.rj.com.playandroid.BaseTest.onToast
import zqx.rj.com.playandroid.main.MainActivity

/**
 *  author: HyJame
 *  date:   2019-11-28
 *  desc:   TODO
 */
@RunWith(AndroidJUnit4::class)
class SLoginProcessTest {

    private val account: String = "1142948328"
    private val password: String = "123456"

    @Rule
    @JvmField
    val activityTestRule = IntentsTestRule(MainActivity::class.java)

    private lateinit var idlingResource: IdlingResource

    @Before
    fun setUp() {
//        idlingResource = activityTestRule.activity.mIdlingResource
    }


    @After
    fun release() {
//        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun test_login_success_by_myself() {

        // 判断 drawer 是关闭的，并且 打开
        onView(withId(R.id.mDrawerMain))
            .check(matches(isClosed(Gravity.START))) // Left Drawer should be closed.
            .perform(open())

        onClick(R.id.mCivIcon)

        // 判断是否 启动了  LoginActivity
        intended(
            allOf(
                hasComponent(hasShortClassName(".account.view.LoginActivity")),
                toPackage("zqx.rj.com.playandroid")
            )
        )


        onEditView(R.id.mTieAccount, account)
        onEditView(R.id.mTiePassword, password)

        onClick(R.id.mBtnLogin)

        IdlingRegistry.getInstance().register(idlingResource)

        activityTestRule.launchActivity(Intent(activityTestRule.activity, MainActivity::class.java))
        onToast(activityTestRule.activity, R.string.login_suc)
    }
}