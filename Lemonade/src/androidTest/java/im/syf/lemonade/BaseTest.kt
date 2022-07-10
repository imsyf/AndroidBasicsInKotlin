package im.syf.lemonade

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.equalTo

/**
 * The lemonade app is effectively a state machine.
 * In order to avoid stateful tests (tests that rely on previous tests),
 * these utility methods move states and reduce code duplication.
 * This class is meant to be inherited by the @Test methods to leverage these methods.
 */
open class BaseTest {

    /**
     * Test to ensure the app is in the correct state.
     * @param textActionResource Integer for the expected text resource.
     * @param drawableResource Integer for the expected drawable resource.
     */
    fun testState(textActionResource: Int, drawableResource: Int) {
        onView(withId(R.id.text_action))
            .check(matches(withText(textActionResource)))
        onView(withId(R.id.image_lemon_state)).check(
            matches(withTagValue(equalTo(drawableResource)))
        )
    }

    /**
     * Clicks the lemon tree image.
     */
    fun pickLemon() {
        onView(withTagValue(equalTo(R.drawable.lemon_tree)))
            .perform(click())
    }

    /**
     * Squeeze the lemon until the lemon image is gone.
     * The number of clicks required is determined by a random number that the test is not
     * aware of, so we loop and click until the image changes.
     */
    fun juiceLemon() {
        while (onView(withTagValue(equalTo(R.drawable.lemon_squeeze))).isPresent()) {
            onView(withId(R.id.image_lemon_state)).perform(click())
        }
    }

    /**
     * Click the lemonade image.
     */
    fun drinkJuice() {
        onView(withTagValue(equalTo(R.drawable.lemon_drink)))
            .perform(click())
    }

    /**
     * Click the empty glass image to restart.
     */
    fun restart() {
        onView(withTagValue(equalTo(R.drawable.lemon_restart)))
            .perform(click())
    }

    /**
     * Extension function to determine if element is present.
     * This is used to click the lemon image until it changes because the number of clicks
     * required is determined by a random number that the test doesn't know about.
     */
    private fun ViewInteraction.isPresent(): Boolean {
        return try {
            check(matches(isDisplayed()))
            true
        } catch (e: NoMatchingViewException) {
            false
        }
    }
}
