package com.joshowen.newsapp

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.joshowen.newsapp.base.BaseFragmentTest
import com.joshowen.newsapp.ui.ArticleAdapter
import com.joshowen.newsapp.utils.waitFor
import kotlinx.coroutines.delay
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test

class ViewArticleTest : BaseFragmentTest() {

    @Before
    override fun setup() {
        super.setup()
        onView(withId(R.id.rvArticles))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArticleAdapter.ArticleViewHolder>(
                    0,
                    click()
                )
            ).perform(waitFor(100))
    }

    @Test
     fun view_successfully_rendered() {

        onView(
            CoreMatchers.allOf(
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
    fun star_article() {
        onView(withId(R.id.starArticle))
            .perform(click())
    }

}