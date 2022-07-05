package com.joshowen.newsapp

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import com.joshowen.newsapp.base.BaseFragmentTest
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test


class SettingsFragmentTest : BaseFragmentTest() {

    @Before
    override fun setup() {
        super.setup()
        openContextualActionModeOverflowMenu()
        onView(withText(R.string.navigation_title_settings)).perform(click())
    }

    @Test
    fun settings_page_loaded() {

        onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                withParent(withId(R.id.toolbar))
            )
        ).check(matches(withText(R.string.page_title_settings)))

        onView(
            Matchers.allOf(
                withText(R.string.settings_dark_theme_title),
                isDescendantOfA(withId(androidx.preference.R.id.recycler_view))
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun settings_page_toggle_dark_mode() {

        onView(withId(androidx.preference.R.id.recycler_view))
            .perform(
                actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText(R.string.settings_dark_theme_title)), click()
                )
            )

    }
}