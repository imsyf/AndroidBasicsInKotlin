package im.syf.dogglers

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import im.syf.dogglers.DrawableMatcher.hasItemCount
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class GridListTests : BaseTest() {

    @get:Rule
    var activityRule = ActivityScenarioRule(GridListActivity::class.java)

    @Test
    fun grid_list_content_at_first_position() {
        checkFirstPosition()
    }

    @Test
    fun grid_list_content_on_first_page() {
        onView(withId(R.id.grid_recycler_view)).perform(
            scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(withText("Nox"))
            )
        )
        onView(withText("Nox")).check(matches(isDisplayed()))
    }

    @Test
    fun grid_list_content_at_last_position() {
        onView(withId(R.id.grid_recycler_view)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(lastPosition)
        )
        onView(withText("Bella")).check(matches(isDisplayed()))
    }

    @Test
    fun vertical_scrolling() {
        onView(withId(R.id.grid_recycler_view)).perform(swipeUp())
        onView(withText("Bella")).check(matches(isDisplayed()))
    }

    @Test
    fun recycler_view_item_count() {
        onView(withId(R.id.grid_recycler_view)).check(hasItemCount(6))
    }
}
