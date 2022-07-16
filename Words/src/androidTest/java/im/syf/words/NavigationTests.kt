package im.syf.words

import android.content.Context
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTests {

    @Test
    fun navigate_to_words_nav_component() {
        val context: Context = ApplicationProvider.getApplicationContext()

        val navController = TestNavHostController(context)

        val letterListScenario = launchFragmentInContainer<LetterListFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )

        letterListScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.recycler_view)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )

        assertEquals(navController.currentDestination?.id, R.id.wordListFragment)
    }
}
