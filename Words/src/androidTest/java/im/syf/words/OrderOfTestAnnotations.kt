package im.syf.words

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OrderOfTestAnnotations {

    @Before
    fun setupFunction() {
        println("Set up function")
    }

    @Test
    fun test_a() {
        println("Test a")
    }

    @Test
    fun test_b() {
        println("Test b")
    }

    @Test
    fun test_c() {
        println("Test c")
    }

    @After
    fun tearDownFunction() {
        println("Tear down function")
    }

    companion object {

        @BeforeClass
        @JvmStatic
        fun setupClass() {
            println("Set up class")
        }

        @BeforeClass
        @JvmStatic
        fun tearDownClass() {
            println("Tear down class")
        }
    }
}
