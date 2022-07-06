package app.trakn.trakn.api.services

import app.trakn.trakn.models.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsfeedService {

    @GET("photos")
    fun getAll(): Call<List<News>>
}