package im.syf.lunchtray

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import im.syf.lunchtray.ui.order.AccompanimentFragment
import im.syf.lunchtray.ui.order.EntreeFragment
import im.syf.lunchtray.ui.order.SideFragment
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class OrderFunctionalityTests : BaseTest() {

    /**
     * Test subtotal in [EntreeFragment]
     *
     * It isn't necessarily best practice to make all these assertions in a single test,
     * however, it is done here for improved readability of the file.
     */
    @Test
    fun radio_buttons_update_entree_menu_subtotal() {
        // Launch the entree menu fragment
        launchFragmentInContainer<EntreeFragment>(themeResId = R.style.Theme_AndroidBasicsInKotlin)

        // Select the cauliflower item
        onView(withId(R.id.cauliflower)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $7.00"))))

        // Select the chili item
        onView(withId(R.id.chili)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $4.00"))))

        // Select the pasta item
        onView(withId(R.id.pasta)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $5.50"))))

        // Select the skillet item
        onView(withId(R.id.skillet)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $5.50"))))
    }

    /**
     * Test subtotal in [SideFragment]
     *
     * It isn't necessarily best practice to make all these assertions in a single test,
     * however, it is done here for improved readability of the file.
     */
    @Test
    fun radio_buttons_update_side_menu_subtotal() {
        // Launch the side menu fragment
        launchFragmentInContainer<SideFragment>(themeResId = R.style.Theme_AndroidBasicsInKotlin)

        // Select the salad item
        onView(withId(R.id.salad)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $2.50"))))

        // Select the soup item
        onView(withId(R.id.soup)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $3.00"))))

        // Select the potato item
        onView(withId(R.id.potatoes)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $2.00"))))

        // Select the rice item
        onView(withId(R.id.rice)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $1.50"))))
    }

    /**
     * Test subtotal in [AccompanimentFragment]
     *
     * It isn't necessarily best practice to make all these assertions in a single test,
     * however, it is done here for improved readability of the file.
     */
    @Test
    fun radio_buttons_update_accompaniment_menu_subtotal() {
        // Launch the side menu fragment
        launchFragmentInContainer<AccompanimentFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )

        // Select the salad item
        onView(withId(R.id.bread)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $0.50"))))

        // Select the soup item
        onView(withId(R.id.berries)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $1.00"))))

        // Select the potato item
        onView(withId(R.id.pickles)).perform(click())
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $0.50"))))
    }

    /**
     * Test subtotals in full order flow
     */
    @Test
    fun subtotal_updates_in_full_order_flow() {
        // Launch the main activity
        launchActivity<MainActivity>()
        // Start order
        onView(withId(R.id.start_order_btn)).perform(click())
        // Select entree item
        onView(withId(R.id.cauliflower)).perform(click())
        // We already have a test for a single menu item selection, so we don't need to check the
        // subtotal here.
        // Move to next fragment
        onView(withId(R.id.next_button)).perform(click())
        // Select side item
        onView(withId(R.id.salad)).perform(click())
        // Check that subtotal has updated
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $9.50"))))
        // Move to next fragment
        onView(withId(R.id.next_button)).perform(click())
        // Select accompaniment item
        onView(withId(R.id.bread)).perform(click())
        // Check that subtotal has updated
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $10.00"))))
        // Move to next fragment
        onView(withId(R.id.next_button)).perform(click())
        // Check that subtotal in checkout is correct
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $10.00"))))
    }

    /**
     * Test subtotal, tax, and total in [im.syf.lunchtray.ui.order.CheckoutFragment]
     */
    @Test
    fun subtotal_tax_total_in_checkout() {
        // Select items and move to checkout
        fullOrderFlow()
        // Check subtotal. Note that this is already done in a separate test, but the other values
        // depend on it so this assertion is a sanity check
        onView(withId(R.id.subtotal))
            .check(matches(withText(containsString("Subtotal: $10.00"))))
        // Check tax
        onView(withId(R.id.tax))
            .check(matches(withText(containsString("Tax: $0.80"))))
        // Check total
        onView(withId(R.id.total))
            .check(matches(withText(containsString("Total: $10.80"))))
    }

    /**
     * Test that the order is reset after canceling in [EntreeFragment]
     */
    @Test
    fun order_reset_after_cancel_from_entree_menu() {
        // Launch the app
        launchActivity<MainActivity>()
        // Start the order
        onView(withId(R.id.start_order_btn)).perform(click())
        // Select an item
        onView(withId(R.id.cauliflower)).perform(click())
        // Cancel order
        onView(withId(R.id.cancel_button)).perform(click())
        // Start the order
        onView(withId(R.id.start_order_btn)).perform(click())
        // Make sure subtotal is zero
        onView(withText("Subtotal: $0.00")).check(matches(isDisplayed()))
    }

    /**
     * Test that the order is reset after canceling in [SideFragment]
     */
    @Test
    fun order_reset_after_cancel_from_side_menu() {
        // Launch the app
        launchActivity<MainActivity>()
        // Start the order
        onView(withId(R.id.start_order_btn)).perform(click())
        // Select an item
        onView(withId(R.id.cauliflower)).perform(click())
        // Move to side menu
        onView(withId(R.id.next_button)).perform(click())
        // Select an item
        onView(withId(R.id.soup)).perform(click())
        // Cancel the order
        onView(withId(R.id.cancel_button)).perform(click())
        // Start the order
        onView(withId(R.id.start_order_btn)).perform(click())
        // Make sure subtotal is zero
        onView(withText("Subtotal: $0.00")).check(matches(isDisplayed()))
    }

    /**
     * Test that the order is reset after canceling in [AccompanimentFragment]
     */
    @Test
    fun order_reset_after_cancel_from_accompaniment_menu() {
        // Launch the app
        launchActivity<MainActivity>()
        // Start the order
        onView(withId(R.id.start_order_btn)).perform(click())
        // Select an item
        onView(withId(R.id.cauliflower)).perform(click())
        // Move to side menu
        onView(withId(R.id.next_button)).perform(click())
        // Select an item
        onView(withId(R.id.soup)).perform(click())
        // Move to accompaniment menu
        onView(withId(R.id.next_button)).perform(click())
        // Select item
        onView(withId(R.id.bread)).perform(click())
        // Cancel the order
        onView(withId(R.id.cancel_button)).perform(click())
        // Start the order
        onView(withId(R.id.start_order_btn)).perform(click())
        // Make sure subtotal is zero
        onView(withText("Subtotal: $0.00")).check(matches(isDisplayed()))
    }

    /**
     * Test that the order is reset after canceling in [im.syf.lunchtray.ui.order.CheckoutFragment]
     */
    @Test
    fun order_reset_after_cancel_from_checkout() {
        // Select items and move to checkout
        fullOrderFlow()
        // Cancel the order
        onView(withId(R.id.cancel_button)).perform(click())
        // Start the order
        onView(withId(R.id.start_order_btn)).perform(click())
        // Make sure subtotal is zero
        onView(withText("Subtotal: $0.00")).check(matches(isDisplayed()))
    }

    /**
     * Test that the correct snackbar is displayed when order is submitted
     */
    @Test
    fun order_snackbar() {
        // Select items and move to checkout
        fullOrderFlow()
        // Click submit
        onView(withId(R.id.submit_button)).perform(click())
        // Check for snackbar text
        onView(withText(containsString("Order Submitted!")))
            .check(matches(isDisplayed()))
    }
}
