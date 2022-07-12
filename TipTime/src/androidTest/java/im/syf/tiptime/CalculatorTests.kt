package im.syf.tiptime

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CalculatorTests : BaseTest() {

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {
        testTip("50.00", "$10.00")
    }

    @Test
    fun calculate_18_percent_tip() {
        testTip("50.00", "$9.00", percentage = R.id.option_eighteen)
    }

    @Test
    fun calculate_15_percent_tip_round_up() {
        testTip("50.00", "$8.00", percentage = R.id.option_fifteen)
    }

    @Test
    fun calculate_15_percent_tip_no_rounding() {
        testTip("50.00", "$7.50", percentage = R.id.option_fifteen, roundTipUp = false)
    }
}
