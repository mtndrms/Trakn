package app.trakn.trakn.api.services

import app.trakn.trakn.models.Cryptocurrency
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoService {

    @GET("coins/markets")
    fun getMarketData(
        @Query("vs_currency") vsCurrency: String,
        @Query("order") order: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("sparkline") sparkline: Boolean
    ): Call<List<Cryptocurrency>>
}