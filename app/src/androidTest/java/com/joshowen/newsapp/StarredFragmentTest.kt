package com.joshowen.newsapp

import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.joshowen.newsapp.base.BaseFragmentTest
import com.joshowen.newsapp.ui.ArticleAdapter
import com.joshowen.newsapp.utils.waitFor
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test

class StarredFragmentTest : BaseFragmentTest() {

    @Before
    override fun setup() {
        super.setup()
        scenario.onActivity {
            it.binding.bnvHomeNavigation.selectedItemId = R.id.starredFragment
        }
    }

    @Test
    fun starredPageLoaded() {

        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText(R.string.page_title_starred)))

        Espresso.onView(ViewMatchers.withId(R.id.rvStarred))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        scenario.onActivity {
            it.binding.bnvHomeNavigation.selectedItemId = R.id.starredFragment
            assert(it.binding.bnvHomeNavigation.selectedItemId == R.id.starredFragment)
        }


    }

    @Test
    fun whenArticleStarredThenShowInStarredScreenSuccessfully() {

        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText(R.string.page_title_starred)))

        Espresso.onView(ViewMatchers.withId(R.id.rvStarred))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        scenario.onActivity {
            it.binding.bnvHomeNavigation.selectedItemId = R.id.articlesFragment
            assert(it.binding.bnvHomeNavigation.selectedItemId == R.id.articlesFragment)
        }

        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(250))

        Espresso.onView(ViewMatchers.withId(R.id.rvArticles))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArticleAdapter.ArticleViewHolder>(
                    0,
                    click()
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.starArticle))
            .perform(click())

        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(250))

        pressBack()

        scenario.onActivity {
            it.binding.bnvHomeNavigation.selectedItemId = R.id.starredFragment
            assert(it.binding.bnvHomeNavigation.selectedItemId == R.id.starredFragment)
        }

        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(250))

        Espresso.onView(ViewMatchers.withId(R.id.rvStarred))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArticleAdapter.ArticleViewHolder>(
                    0,
                    click()
                )
            )

        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText(R.string.page_title_article)))

        Espresso.onView(ViewMatchers.withId(R.id.starArticle))
            .perform(click())

        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(250))
    }

    @Test
    fun navigateToSettingsAndReturn() {
        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText(R.string.page_title_starred)))

        Espresso.openContextualActionModeOverflowMenu()
        Espresso.onView(ViewMatchers.withText(R.string.navigation_title_settings)).perform(click())

        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText(R.string.page_title_settings)))

        pressBack()

        Espresso.onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.toolbar))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText(R.string.page_title_starred)))
    }
}