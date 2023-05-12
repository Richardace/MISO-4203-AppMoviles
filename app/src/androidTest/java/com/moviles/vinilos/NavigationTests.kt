package com.moviles.vinilos

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moviles.vinilos.ui.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTests {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.moviles.vinilos", appContext.packageName)
    }

    @Test
    fun testNavigationFromMainToColecionistas() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.SaveArtistbutton)).perform(ViewActions.click())
        onView(withText("Coleccionistas")).check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationFromMainToVisitanteHome() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.visitorButton)).perform(ViewActions.click())
        onView(withText("Hola Visitante")).check(matches(isDisplayed()))
    }
}