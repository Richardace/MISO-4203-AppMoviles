package com.moviles.vinilos

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moviles.vinilos.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class AlbumTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.moviles.vinilos", appContext.packageName)
    }

    @Test
    fun testExistsAlbum() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.visitorButton)).perform(ViewActions.click())
        onView(withText("Hola Visitante")).check(matches(isDisplayed()))
        onView(withId(R.id.AlbumButton)).perform(ViewActions.click())
        onView(withText("Albumes")).check(matches(isDisplayed()))
        onView(withId(R.id.band_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testExistsAlbumList() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.visitorButton)).perform(ViewActions.click())
        onView(withText("Hola Visitante")).check(matches(isDisplayed()))
        onView(withId(R.id.AlbumButton)).perform(ViewActions.click())
        onView(withText("Albumes")).check(matches(isDisplayed()))
        onView(withId(R.id.band_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationFromMainToAlbums() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(ViewMatchers.withId(R.id.visitorButton)).perform(ViewActions.click())
        onView(withText("Hola Visitante")).check(matches(isDisplayed()))
        onView(withId(R.id.AlbumButton)).perform(ViewActions.click())
        onView(withText("Sueño")).check(matches(isDisplayed()))
    }

}