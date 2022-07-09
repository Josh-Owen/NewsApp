package com.joshowen.newsapp
//
//import android.widget.TextView
//import androidx.test.espresso.Espresso
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.matcher.ViewMatchers
//import androidx.test.espresso.matcher.ViewMatchers.*
//import androidx.test.platform.app.InstrumentationRegistry
//import com.joshowen.newsapp.base.BaseFragmentTest
//import com.joshowen.newsapp.utils.waitFor
//import org.hamcrest.CoreMatchers.allOf
//import org.hamcrest.CoreMatchers.instanceOf
//import org.hamcrest.Matchers
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//
//
//class StarredFragmentOfflineTest : BaseFragmentTest() {
//
//
//    @Before
//    override fun setup() {
//        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi disable")
//        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data disable")
//        super.setup()
//    }
//
//    @After
//    fun tearDown() {
//        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc wifi enable")
//        InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand("svc data enable")
//        onView(isRoot()).perform(waitFor(1000))
//    }
//
//    @Test
//    fun starredPageLoaded() {
//
//        onView(
//            Matchers.allOf(
//                withId(R.drawable.ic_article),
//                withId(R.drawable.ic_star),
//                isDescendantOfA(withId(R.id.bnvHomeNavigation))
//            )
//        )
//
//        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
//            .check(matches(withText(R.string.page_title_starred)))
//    }
//
//    @Test
//    fun failureToLoadArticles() {
//        onView(withId(R.id.btnRetry))
//            .check(matches(isDisplayed()))
//    }
//}
