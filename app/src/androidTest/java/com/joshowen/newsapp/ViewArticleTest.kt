package com.joshowen.newsapp

import android.content.Intent
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.joshowen.newsapp.base.BaseFragmentTest
import com.joshowen.newsapp.ui.ArticleAdapter
import com.joshowen.newsapp.utils.waitFor
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Test


class ViewArticleTest : BaseFragmentTest() {

    @Before
    override fun setup() {
        super.setup()
        onView(ViewMatchers.isRoot()).perform(waitFor(250))
        onView(withId(R.id.rvArticles))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArticleAdapter.ArticleViewHolder>(
                    0,
                    click()
                )
            )
    }

    @Test
    fun viewArticleLoaded() {

        onView(
            allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(withId(R.id.toolbar))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText(R.string.page_title_article)))

        onView(withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.tvAuthor))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.tvDescriptionBody))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.tvContentBody))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.btnOpenArticle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun starArticle() {
        onView(withId(R.id.starArticle))
            .perform(click())
        onView(ViewMatchers.isRoot()).perform(waitFor(250))
    }

    @Test
    fun starAndUnStar() {
        onView(withId(R.id.starArticle))
            .perform(click())
        onView(ViewMatchers.isRoot()).perform(waitFor(250))
        onView(withId(R.id.starArticle))
            .perform(click())
        onView(ViewMatchers.isRoot()).perform(waitFor(250))
    }

    @Test
    fun navigateToSettingsAndReturn() {
        onView(
            allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(withId(R.id.toolbar))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText(R.string.page_title_article)))

        Espresso.openContextualActionModeOverflowMenu()
        onView(ViewMatchers.withText(R.string.navigation_title_settings)).perform(click())

        onView(
            allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(withId(R.id.toolbar))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText(R.string.page_title_settings)))

        pressBack()

        onView(
            allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                ViewMatchers.withParent(withId(R.id.toolbar))
            )
        ).check(ViewAssertions.matches(ViewMatchers.withText(R.string.page_title_article)))
    }

//    @Test
//    fun seeFullArticleClicked() {
//        onView(withId(R.id.btnOpenArticle)).perform(click())
//        intended(
//            allOf(
//                hasAction(Intent.ACTION_VIEW)
//            )
//        )
//    }
}