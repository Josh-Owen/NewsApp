package com.joshowen.newsrepository

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.joshowen.newsrepository.repos.NewsRepositoryImpl
import com.joshowen.newsrepository.retrofit.NewsService
import com.joshowen.newsrepository.retrofit.request.TopStoriesArticleResponse
import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@RunWith(MockitoJUnitRunner::class)
class NewsServiceInstrumentationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

//    @Mock
//    @Inject
//    lateinit var newsRepo : NewsRepositoryImpl
//
    @Mock
    private lateinit var service: NewsService

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.joshowen.newsrepository.test", appContext.packageName)
    }

//    @Test
//    @ExperimentalCoroutinesApi
//    fun getData() = runTest {
//        this.launch {
//
//            assert(true)
//            val response = TopStoriesResponse(
//                "",
//                5,
//                listOf(
//                    TopStoriesArticleResponse(
//                        "Title",
//                        "Author",
//                        "Description",
//                        "URL",
//                        "ImageURL",
//                        "",
//                        ""
//                    )
//                )
//            )
//
//
//            Mockito.`when`(
//                newsService.getStoriesByTopicPaginated(
//                    ARTICLE_SEARCH_QUERY,
//                    STARTING_PAGE_INDEX,
//                    PAGINATION_PAGE_SIZE
//                )
//            )
//                .thenReturn(Response.success(200, response))
//
//            val result = newsService.getStoriesByTopicPaginated(
//                ARTICLE_SEARCH_QUERY,
//                STARTING_PAGE_INDEX,
//                PAGINATION_PAGE_SIZE
//            )
//
//
//
//
//            assertEquals(result.code(), 200)
//
//
//        }
//
//    }

}