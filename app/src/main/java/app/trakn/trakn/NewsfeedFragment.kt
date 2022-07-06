package app.trakn.trakn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.trakn.trakn.api.ApiClient
import app.trakn.trakn.api.services.NewsfeedService
import app.trakn.trakn.models.News
import app.trakn.trakn.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsfeedFragment : Fragment() {
    private lateinit var newsfeedService: NewsfeedService
    private lateinit var newsList: MutableList<News>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_newsfeed, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiClient = ApiClient(Constants.NEWS_API_URL)
        newsfeedService = apiClient.retrofit?.create(NewsfeedService::class.java)!!

        val news = newsfeedService.getAll()
        news.enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.isSuccessful) {
                    newsList = (response.body() as MutableList<News>)
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                println(t.message.toString())
            }
        })
    }
}