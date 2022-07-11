package com.joshowen.newsrepository

import com.joshowen.newsrepository.base.BaseUnitTest
import org.junit.Test

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class NewsServiceTest : BaseUnitTest() {

    @Before
    override fun setup() {
        super.setup()
    }

    @Test
    @ExperimentalCoroutinesApi
    fun fetchedArticlesResponseOK() = runTest {

        val responseModel =
            PaginatedArticlesAPIResponseFactory.getApiResponse(PaginatedArticlesAPIResponseFactory.ResponseType.COMPLETE_PAGINATED_PAGE)

        Mockito.`when`(
            newsService.getStoriesByTopicPaginated(
                ARTICLE_SEARCH_QUERY,
                STARTING_PAGE_INDEX,
                PAGINATION_PAGE_SIZE
            )
        ).thenReturn(responseModel)

        val apiResponse = newsService.getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        verify(newsService, times(1)).getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        assert(apiResponse.isSuccessful)
    }


    @Test
    @ExperimentalCoroutinesApi
    fun fetchedArticlesResponseError() = runTest {

        val responseModel =
            PaginatedArticlesAPIResponseFactory.getApiResponse(PaginatedArticlesAPIResponseFactory.ResponseType.API_ERROR)

        Mockito.`when`(
            newsService.getStoriesByTopicPaginated(
                ARTICLE_SEARCH_QUERY,
                STARTING_PAGE_INDEX,
                PAGINATION_PAGE_SIZE
            )
        ).thenReturn(responseModel)

        val apiResponse = newsService.getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        verify(newsService, times(1)).getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        assert(!apiResponse.isSuccessful)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun fetchedCorrectNumberOfArticles() = runTest {

        val responseModel =
            PaginatedArticlesAPIResponseFactory.getApiResponse(PaginatedArticlesAPIResponseFactory.ResponseType.COMPLETE_PAGINATED_PAGE)

        Mockito.`when`(
            newsService.getStoriesByTopicPaginated(
                ARTICLE_SEARCH_QUERY,
                STARTING_PAGE_INDEX,
                PAGINATION_PAGE_SIZE
            )
        ).thenReturn(responseModel)

        val apiResponse = newsService.getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        verify(newsService, times(1)).getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        assert(apiResponse.body()?.articles?.size == PAGINATION_PAGE_SIZE)
    }


    @Test
    @ExperimentalCoroutinesApi
    fun fetchedNoResults() = runTest {

        val responseModel =
            PaginatedArticlesAPIResponseFactory.getApiResponse(PaginatedArticlesAPIResponseFactory.ResponseType.NO_RESULTS)

        Mockito.`when`(
            newsService.getStoriesByTopicPaginated(
                ARTICLE_SEARCH_QUERY,
                STARTING_PAGE_INDEX,
                PAGINATION_PAGE_SIZE
            )
        ).thenReturn(responseModel)

        val apiResponse = newsService.getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        verify(newsService, times(1)).getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        assert(apiResponse.isSuccessful && apiResponse.body()?.articles?.size == 0)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun fetchedIncompleteListOfArticles() = runTest {

        val responseModel =
            PaginatedArticlesAPIResponseFactory.getApiResponse(PaginatedArticlesAPIResponseFactory.ResponseType.INCOMPLETE_NUMBER_OF_RESULTS)

        Mockito.`when`(
            newsService.getStoriesByTopicPaginated(
                ARTICLE_SEARCH_QUERY,
                STARTING_PAGE_INDEX,
                PAGINATION_PAGE_SIZE
            )
        ).thenReturn(responseModel)

        val apiResponse = newsService.getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        verify(newsService, times(1)).getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        assert(apiResponse.isSuccessful && apiResponse.body()?.articles?.size != PAGINATION_PAGE_SIZE)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun fetchedMismatchedNumberOfArticles() = runTest {

        val responseModel =
            PaginatedArticlesAPIResponseFactory.getApiResponse(PaginatedArticlesAPIResponseFactory.ResponseType.API_RESULTS_COUNT_MISMATCH)

        Mockito.`when`(
            newsService.getStoriesByTopicPaginated(
                ARTICLE_SEARCH_QUERY,
                STARTING_PAGE_INDEX,
                PAGINATION_PAGE_SIZE
            )
        ).thenReturn(responseModel)

        val apiResponse = newsService.getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        verify(newsService, times(1)).getStoriesByTopicPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        assert(apiResponse.isSuccessful && apiResponse.body()?.totalResults != apiResponse.body()?.articles?.size)
    }
}