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
class ArtistListTests {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.moviles.vinilos", appContext.packageName)
    }

    @Test
    fun testListExists() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.visitorButton)).perform(ViewActions.click())
        onView(withText("Hola Visitante")).check(matches(isDisplayed()))
        onView(withId(R.id.artistButton)).perform(ViewActions.click())
        onView(withText("Artistas")).check(matches(isDisplayed()))
        onView(withId(R.id.band_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun testAddArtist() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.visitorButton)).perform(ViewActions.click())
        onView(withText("Hola Visitante")).check(matches(isDisplayed()))
        onView(withId(R.id.artistButton)).perform(ViewActions.click())
        onView(withText("Artistas")).check(matches(isDisplayed()))
        onView(withId(R.id.addartistbutton)).perform(ViewActions.click())
        onView(withText("Crear Artista")).check(matches(isDisplayed()))
        onView(withId(R.id.nombreArtista)).check(matches(isDisplayed()));
        onView(withId(R.id.imagenArtista)).check(matches(isDisplayed()));
        onView(withId(R.id.descripcionArtista)).check(matches(isDisplayed()));
        onView(withId(R.id.birthDateArtista)).check(matches(isDisplayed()));
        onView(withId(R.id.SaveArtistbutton)).check(matches(isDisplayed()));
    }
}