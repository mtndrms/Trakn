package app.trakn.trakn.api.services

import app.trakn.trakn.models.responses.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsfeedService {

    @GET("news")
    fun getAllCryptocurrencyNews(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
        @Query("toDate") toDate: Long?,
        @Query("fromDate") fromDate: Long?
    ): Call<News>
}