package com.remonh87.comfortfood

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class AppNavigationTest {

    @get:Rule // unresolved reference here
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun showPlaceHolderScreen() {
        onView(withId(R.id.launch_pizza_button)).check(matches(withText("Luigi's pizza")))
    }
}
