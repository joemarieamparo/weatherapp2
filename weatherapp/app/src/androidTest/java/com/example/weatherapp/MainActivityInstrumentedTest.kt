package com.example.weatherapp

import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.weatherapp.utils.EspressoIdlingResouce
import com.example.weatherapp.view.MainActivity
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        IdlingRegistry.getInstance().register(EspressoIdlingResouce.idlingResource)
    }

    @Test
    fun search_display_forecast() {
        //Start searching
        onView(withId(R.id.menu_search)).perform(ViewActions.click())
        onView(withHint("Enter city name"))
            .perform(click())
            .perform(
                typeText("Cebu City"),
                pressKey(KeyEvent.KEYCODE_ENTER)
            )
        onView(withText("Cebu City"))
            .check(matches(isDisplayed()))

        //Clicking the first item in the list
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))

        onView(withId(R.id.textview_today)).check(matches(isDisplayed()))
    }

    @Test
    fun search_no_result() {
        //Start searching
        onView(withId(R.id.menu_search)).perform(click())
        onView(withHint("Enter city name"))
            .perform(click())
            .perform(
                typeText("dfd1212"),
                pressKey(KeyEvent.KEYCODE_ENTER)
            )

        onView(withText(R.string.no_result_found))
            .inRoot(withDecorView(not(mActivityRule.activity.window.decorView)))// Here we use decorView
            .check(matches(isDisplayed()))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResouce.idlingResource)
    }
}
