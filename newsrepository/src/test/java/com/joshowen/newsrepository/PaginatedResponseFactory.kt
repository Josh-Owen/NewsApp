package com.joshowen.newsrepository

import com.joshowen.newsrepository.retrofit.request.TopStoriesArticleResponse
import com.joshowen.newsrepository.retrofit.request.TopStoriesResponse
import com.joshowen.newsrepository.retrofit.request.mapToArticle
import com.joshowen.newsrepository.room.models.Article
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object PaginatedResponseFactory {

    enum class ResponseType {
        COMPLETE_PAGINATED_PAGE, API_ERROR, NO_RESULTS, INCOMPLETE_NUMBER_OF_RESULTS, API_RESULTS_COUNT_MISMATCH
    }

    private val article1 = TopStoriesArticleResponse(
        "Stop Checking Your 401k So Much",
        "Lindsey Ellefson",
        "Now more than ever, heed this advice: Stop looking at your 401k so damn much. When there’s alarming news about the stock market (see: every last shred of economic news at the moment), you might feel compelled to obsess about how your retirement savings are fa…",
        "https://lifehacker.    com/stop-checking-your-401k-so-much-1849054622",
        "https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/e75fb5712eb9440b755342db0b710ed6.jpg",
        "2022-06-13T19:58:02Z",
        "Now more than ever, heed this advice: Stop looking at your 401k so damn much. When theres alarming news about the stock market (see: every last shred of economic news at the moment), you might feel c… [+2896 chars]"
    )

    private val article2 = TopStoriesArticleResponse(
        "What You Should Do Now to Prepare for a Recession",
        "Meredith Dietz",
        "Not to be too alarmist, but the economy isn’t looking super great at the moment. For starters, don’t look at your 401(k) right now. From crashing stock markets to spiking consumer prices, a recession seems to be looming. Although the news is troubling, there …",
        "https://lifehacker.com/what-you-should-do-now-to-prepare-for-a-recession-1849129353",
        "https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/1068939d0fdd14ff2bed611e5dda52bc.jpg",
        "2022-06-30T18:30:00Z",
        "Not to be too alarmist, but the economy isnt looking super great at the moment. For starters, dont look at your 401(k) right now. From crashing stock markets to spiking consumer prices, a recession s… [+3315 chars]"
    )


    fun sampleArticleResponse1() : Article{
        return article1.mapToArticle()
    }

    fun sampleArticleResponse2() : Article{
        return article2.mapToArticle()
    }

    fun getApiResponse(type: ResponseType) : Response<TopStoriesResponse>{
        return when (type) {
            ResponseType.COMPLETE_PAGINATED_PAGE -> {
                Response.success(
                    TopStoriesResponse(
                        "200",
                        10,
                        listOf(
                            article1,
                            article2,
                            article1,
                            article2,
                            article1,
                            article2,
                            article1,
                            article2,
                            article1,
                            article2
                        )
                    )
                )
            }
            ResponseType.NO_RESULTS -> {
                Response.success(
                    TopStoriesResponse(
                        "200",
                        0,
                        listOf()
                    )
                )
            }
            ResponseType.INCOMPLETE_NUMBER_OF_RESULTS -> {
                Response.success(
                    TopStoriesResponse(
                        "200",
                        9,
                        listOf(
                            article1,
                            article2,
                            article1,
                            article2,
                            article1,
                            article2,
                            article1,
                            article2,
                            article1,
                        )
                    )
                )
            }
            ResponseType.API_ERROR -> {
                Response.error(
                    401,
                    "Error"
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
            }
            ResponseType.API_RESULTS_COUNT_MISMATCH -> {
                Response.success(
                    TopStoriesResponse(
                        "200",
                        9,
                        listOf(
                            article1,
                            article2,
                            article1,
                            article2,
                            article1,
                            article2,
                            article1,
                            article2,
                            article1,
                            article2
                        )
                    )
                )
            }
        }
    }
}
