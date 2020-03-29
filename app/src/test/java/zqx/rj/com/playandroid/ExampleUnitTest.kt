package zqx.rj.com.playandroid

import androidx.fragment.app.Fragment
import com.zhan.ktwing.ext.tryCatch
import org.junit.Test

import org.junit.Assert.*
import zqx.rj.com.playandroid.main.home.view.fragment.HomeFragment
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        val a: Int? = null

        a?.let {
            println("Hello")
            return@let 1
        } ?: doSomething()


        //println(result)
    }

    private fun doSomething() {
        println("World")
    }


}
