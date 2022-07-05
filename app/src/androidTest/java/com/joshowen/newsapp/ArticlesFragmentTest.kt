package com.joshowen.newsapp

import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.joshowen.newsapp.base.BaseFragmentTest
import com.joshowen.newsapp.ui.ArticleAdapter
import com.joshowen.newsapp.utils.waitFor
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test


class ArticlesFragmentTest : BaseFragmentTest() {


    @Before
    override fun setup() {
        super.setup()
    }

    @Test
    fun articlesPageLoaded() {
        onView(
            Matchers.allOf(
                withId(R.drawable.ic_article),
                withId(R.drawable.ic_star),
                isDescendantOfA(withId(R.id.bnvHomeNavigation))
            )
        )

        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
            .check(matches(withText(R.string.page_title_articles)))

        onView(withId(R.id.rvArticles))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToSelectedArticle() {
        onView(isRoot()).perform(waitFor(250))
        onView(withId(R.id.rvArticles))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArticleAdapter.ArticleViewHolder>(
                    0,
                    click()
                )
            )

    }

    @Test
    fun navigateToSettingsAndReturn() {
        onView(
            allOf(
                instanceOf(TextView::class.java),
                withParent(withId(R.id.toolbar))
            )
        ).check(matches(withText(R.string.page_title_articles)))

        Espresso.openContextualActionModeOverflowMenu()
        onView(withText(R.string.navigation_title_settings)).perform(click())

        onView(
            allOf(
                instanceOf(TextView::class.java),
                withParent(withId(R.id.toolbar))
            )
        ).check(matches(withText(R.string.page_title_settings)))

        Espresso.pressBack()

        onView(
            allOf(
                instanceOf(TextView::class.java),
                withParent(withId(R.id.toolbar))
            )
        ).check(matches(withText(R.string.page_title_articles)))
    }
}