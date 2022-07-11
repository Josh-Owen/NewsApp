package com.joshowen.newsrepository

import com.joshowen.newsrepository.PaginatedResponseFactory.sampleArticleResponse1
import com.joshowen.newsrepository.base.BaseUnitTest
import com.joshowen.newsrepository.retrofit.wrappers.ResultWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito

class NewsRepositoryTest : BaseUnitTest() {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPaginatedArticlesOK() = runTest {

        val responseModel =
            PaginatedResponseFactory.getApiResponse(PaginatedResponseFactory.ResponseType.COMPLETE_PAGINATED_PAGE)

        Mockito.`when`(
            newsRepository.getAllStoriesPaginated(
                ARTICLE_SEARCH_QUERY,
                STARTING_PAGE_INDEX,
                PAGINATION_PAGE_SIZE
            )
        ).thenReturn(ResultWrapper.Success(responseModel))

        val apiResponse = newsRepository.getAllStoriesPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        Mockito.verify(newsRepository, Mockito.times(1)).getAllStoriesPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        assert(apiResponse is ResultWrapper.Success)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPaginatedArticlesNetworkError() = runTest {

        Mockito.`when`(
            newsRepository.getAllStoriesPaginated(
                ARTICLE_SEARCH_QUERY,
                STARTING_PAGE_INDEX,
                PAGINATION_PAGE_SIZE
            )
        ).thenReturn(ResultWrapper.NetworkError("Error"))

        val apiResponse = newsRepository.getAllStoriesPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        Mockito.verify(newsRepository, Mockito.times(1)).getAllStoriesPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        assert(apiResponse is ResultWrapper.NetworkError)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPaginatedArticlesNetworkException() = runTest {

        Mockito.`when`(
            newsRepository.getAllStoriesPaginated(
                ARTICLE_SEARCH_QUERY,
                STARTING_PAGE_INDEX,
                PAGINATION_PAGE_SIZE
            )
        ).thenReturn(ResultWrapper.NetworkException(Exception("Exception")))

        val apiResponse = newsRepository.getAllStoriesPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        Mockito.verify(newsRepository, Mockito.times(1)).getAllStoriesPaginated(
            ARTICLE_SEARCH_QUERY,
            STARTING_PAGE_INDEX,
            PAGINATION_PAGE_SIZE
        )

        assert(apiResponse is ResultWrapper.NetworkException)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun hasArticleWithId() = runTest {
        val article = sampleArticleResponse1()

        Mockito.`when`(
            newsRepository.hasArticleWithId(article.id)
        ).thenReturn(true)

        val hasArticleWithId = newsRepository.hasArticleWithId(article.id)
        assert(hasArticleWithId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun hasNoArticleWithId() = runTest {
        val article = sampleArticleResponse1()

        Mockito.`when`(
            newsRepository.hasArticleWithId(article.id)
        ).thenReturn(false)

        val hasArticleWithId = newsRepository.hasArticleWithId(article.id)
        assert(!hasArticleWithId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertArticle() = runTest {

        val article = sampleArticleResponse1()

        newsRepository.insertArticle(article)

        Mockito.`when`(
            newsRepository.hasArticleWithId(article.id)
        ).thenReturn(true)

        val hasArticleWithId = newsRepository.hasArticleWithId(article.id)
        assert(hasArticleWithId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun updateArticleStarred() = runTest {

        val originalArticle = sampleArticleResponse1().apply {
            isStarred = false
        }

        val updatedArticle = sampleArticleResponse1().apply {
            isStarred = true
        }


        Mockito.`when`(
            newsRepository.updateArticle(updatedArticle)
        ).thenReturn(Unit)

        newsRepository.updateArticle(updatedArticle)

        Mockito.verify(newsRepository, Mockito.times(1)).updateArticle(updatedArticle)
        assert(originalArticle.isStarred != updatedArticle.isStarred)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchPaginatedStarredArticles() = runTest {
        assert(true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchPaginatedArticles() = runTest {
        assert(true)
    }
}