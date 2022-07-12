package im.syf.tiptime

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.containsString

open class BaseTest {

    fun testTip(
        cost: String,
        expectedTip: String,
        @IdRes percentage: Int = R.id.option_twenty,
        roundTipUp: Boolean = true
    ) {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText(cost), closeSoftKeyboard())
        onView(withId(percentage))
            .perform(click())
        if (!roundTipUp) {
            onView(withId(R.id.round_up_switch))
                .perform(click())
        }
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString(expectedTip))))
    }
}
