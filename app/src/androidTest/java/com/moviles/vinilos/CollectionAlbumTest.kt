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
import org.hamcrest.core.AllOf
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class CollectionAlbumTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.moviles.vinilos", appContext.packageName)
    }

    @Test
    fun testExistsAlbumCollection() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.SaveArtistbutton)).perform(ViewActions.click())
        onView(withText("Mis Colecciones")).check(matches(isDisplayed()))
    }

    @Test
    fun testAddAlbumCollection() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.SaveArtistbutton)).perform(ViewActions.click())
        onView(withText("Mis Colecciones")).check(matches(isDisplayed()))
        onView(withId(R.id.addalbumcoleccion)).perform(ViewActions.click())
        onView(withText("Albumes")).check(matches(isDisplayed()))
    }

}