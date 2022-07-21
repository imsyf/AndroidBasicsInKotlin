package im.syf.amphibians

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.util.TreeIterables
import java.lang.Thread.sleep
import org.hamcrest.Matcher

open class BaseTest {

    companion object {
        fun lookFor(matcher: Matcher<View>): ViewAction = object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()

            override fun getDescription(): String = "Looking for $matcher"

            override fun perform(uiController: UiController?, view: View?) {
                var attempts = 0
                val childViews: Iterable<View> = TreeIterables.breadthFirstViewTraversal(view)

                childViews.forEach {
                    attempts++
                    if (matcher.matches(it)) return
                }

                throw NoMatchingViewException.Builder()
                    .withRootView(view)
                    .withViewMatcher(matcher)
                    .build()
            }
        }
    }

    fun waitForView(
        matcher: Matcher<View>,
        timeoutMillis: Int = 5000,
        attemptTimeoutMillis: Long = 100
    ): ViewInteraction {
        val maxAttempts = timeoutMillis / attemptTimeoutMillis.toInt()
        var attempts = 0

        for (i in 0..maxAttempts) {
            try {
                attempts++
                onView(isRoot()).perform(lookFor(matcher))
                return onView(matcher)
            } catch (e: Exception) {
                if (attempts == maxAttempts) {
                    throw e
                }
                sleep(attemptTimeoutMillis)
            }
        }

        throw Exception("Could not find a view matching $matcher")
    }
}
