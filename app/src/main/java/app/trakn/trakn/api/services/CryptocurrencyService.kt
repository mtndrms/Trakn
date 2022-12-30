package app.trakn.trakn.api.services

import app.trakn.trakn.models.responses.Coin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptocurrencyService {

    @GET("coins")
    fun getMarketData(
        @Query("currency") currency: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): Call<Coin>
}