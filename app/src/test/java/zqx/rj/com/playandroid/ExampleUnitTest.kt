package zqx.rj.com.playandroid

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testString() {


        assertEquals("1;2;3", listOf(1, 2, 3).joinToString(";"))
        assertEquals("", ArrayList<String>().joinToString(";"))
        assertEquals("1", listOf(1).joinToString(";"))
    }

    @Test
    fun testList(){
        val a:List<Int>? = arrayListOf(1, 2)
        println(a?.get(0))
        println(a?.get(1) )
        println(a?.get(2) ?:  "$(-1)")
    }
}
