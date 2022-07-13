package im.syf.dogglers

import androidx.annotation.DrawableRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import im.syf.dogglers.DrawableMatcher.withDrawable
import im.syf.dogglers.data.DataSource

open class BaseTest {

    val lastPosition = DataSource.dogs.size - 1

    /**
     * Check the content of a card
     *
     * @param name The name string as it appears on the screen
     * @param age The full age string as it appears on the screen
     * @param hobbies The full hobbies string as it appears on the screen
     * @param imageResource The image resource Id
     */
    private fun hasListItemContent(
        name: String,
        age: String,
        hobbies: String,
        @DrawableRes imageResource: Int
    ) {
        onView(withText(name))
            .check(matches(isDisplayed()))
        onView(withText(age))
            .check(matches(isDisplayed()))
        onView(withText(hobbies))
            .check(matches(isDisplayed()))
        onView(withDrawable(imageResource))
            .check(matches(isDisplayed()))
    }

    /**
     * Check the content of the first card
     */
    fun checkFirstPosition() {
        hasListItemContent("Tzeitel", "7", "sunbathing", R.drawable.tzeitel)
    }
}
