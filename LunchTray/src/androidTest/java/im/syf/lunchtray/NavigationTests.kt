package im.syf.lunchtray

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import im.syf.lunchtray.ui.order.AccompanimentFragment
import im.syf.lunchtray.ui.order.CheckoutFragment
import im.syf.lunchtray.ui.order.EntreeFragment
import im.syf.lunchtray.ui.order.SideFragment
import im.syf.lunchtray.ui.order.StartOrderFragment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for all navigation flows
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigationTests : BaseTest() {

    /**
     * Test navigation from [StartOrderFragment] to [EntreeFragment]
     */
    @Test
    fun navigate_to_entree_menu_from_start_order() {
        // Init nav controller
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        // Launch StartOrderFragment
        val startOrderScenario = launchFragmentInContainer<StartOrderFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )
        // Configure nav controller
        startOrderScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // Click start order
        onView(withId(R.id.start_order_btn)).perform(click())
        // Check destination is correct
        assertEquals(navController.currentDestination?.id, R.id.entreeFragment)
    }

    /**
     * Test navigation from [EntreeFragment] to [StartOrderFragment]
     */
    @Test
    fun navigate_to_start_order_from_entree_menu() {
        // Init nav controller
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        // Launch EntreeMenuFragment
        val entreeMenuScenario = launchFragmentInContainer<EntreeFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )
        // Configure nav controller
        entreeMenuScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            // Destination defaults to the home fragment, we have to explicitly set the current
            // destination
            navController.setCurrentDestination(destId = R.id.entreeFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // Click the cancel button
        onView(withId(R.id.cancel_button)).perform(click())
        // Check that the destination is correct
        assertEquals(navController.currentDestination?.id, R.id.startOrderFragment)
    }

    /**
     * Test navigation from [EntreeFragment] to [SideFragment]
     */
    @Test
    fun navigate_to_side_menu_from_entree_menu() {
        // Init nav controller
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        // Launch the EntreeFragment
        val entreeMenuScenario = launchFragmentInContainer<EntreeFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )
        // Configure nav controller
        entreeMenuScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            // Destination defaults to the home fragment, we have to explicitly set the current
            // destination
            navController.setCurrentDestination(destId = R.id.entreeFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // Click the next button
        onView(withId(R.id.next_button)).perform(click())
        // Check that the destination is correct
        assertEquals(navController.currentDestination?.id, R.id.sideFragment)
    }

    /**
     * Test navigation from [SideFragment] to [StartOrderFragment]
     */
    @Test
    fun navigate_to_start_order_from_side_menu() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val sideMenuScenario = launchFragmentInContainer<SideFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )
        sideMenuScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.sideFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.cancel_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrderFragment)
    }

    /**
     * Test navigation from [SideFragment] to [AccompanimentFragment]
     */
    @Test
    fun navigate_to_accompaniment_menu_from_side_menu() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val sideMenuScenario = launchFragmentInContainer<SideFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )
        sideMenuScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.sideFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.next_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.accompanimentFragment)
    }

    /**
     * Test navigation from [AccompanimentFragment] to [StartOrderFragment]
     */
    @Test
    fun navigate_to_start_order_from_accompaniment_menu() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val accompanimentMenuScenario = launchFragmentInContainer<AccompanimentFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )
        accompanimentMenuScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.accompanimentFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.cancel_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrderFragment)
    }

    /**
     * Test navigation from [AccompanimentFragment] to [CheckoutFragment]
     */
    @Test
    fun navigate_to_checkout_from_accompaniment_menu() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val accompanimentMenuScenario = launchFragmentInContainer<AccompanimentFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )
        accompanimentMenuScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.accompanimentFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.next_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.checkoutFragment)
    }

    /**
     * Test navigation from [CheckoutFragment] to [StartOrderFragment]
     */
    @Test
    fun navigate_to_start_order_from_checkout() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val checkoutScenario = launchFragmentInContainer<CheckoutFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )
        checkoutScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.checkoutFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.cancel_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrderFragment)
    }

    /**
     * Test Navigation from [CheckoutFragment] to [StartOrderFragment]
     */
    @Test
    fun navigate_to_start_order_from_checkout_via_submit() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val checkoutScenario = launchFragmentInContainer<CheckoutFragment>(
            themeResId = R.style.Theme_AndroidBasicsInKotlin
        )
        checkoutScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.checkoutFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.submit_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrderFragment)
    }
}
