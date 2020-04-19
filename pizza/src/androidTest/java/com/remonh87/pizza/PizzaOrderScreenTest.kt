package com.remonh87.pizza

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//TODO for now I use production catalogue but should be able to inject mock viewmodel
@RunWith(AndroidJUnit4::class)
class PizzaOrderScreenTest {

    @get:Rule // unresolved reference here
    val activityRule = ActivityTestRule(PizzaOrderActivity::class.java)

    @Test
    fun automaticallyCheckMediumOnLaunch(){
        onView(withId(R.id.medium_selection)).check(matches(isChecked()))
    }

    @Test
    fun updatesPriceAfterSelectingXl(){
        onView(withId(R.id.xl_selection)).perform(click())
        onView(withId(R.id.pizza_price)).check(matches(withText("9.60")))
    }


}
