package app.trakn.trakn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.trakn.trakn.adapters.NewsfeedRecyclerViewAdapter
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
    private lateinit var newsfeedRecyclerViewAdapter: NewsfeedRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val topBar: RelativeLayout = requireActivity().findViewById(R.id.topBar)
        topBar.setBackgroundColor(
            ContextCompat.getColor(requireActivity(), R.color.newsfeed)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_newsfeed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiClient = ApiClient(Constants.NEWS_API_URL)
        val rvNewsList: RecyclerView = view.findViewById(R.id.newsList)

        //TODO: Fix this. (!!)
        newsfeedService = apiClient.retrofit?.create(NewsfeedService::class.java)!!

        val news = newsfeedService.getAll()
        news.enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.isSuccessful) {
                    newsList = (response.body() as MutableList<News>)
                    newsfeedRecyclerViewAdapter = NewsfeedRecyclerViewAdapter(newsList)
                    rvNewsList.adapter = newsfeedRecyclerViewAdapter
                    rvNewsList.layoutManager = LinearLayoutManager(requireContext())
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                println(t.message.toString())
            }
        })
    }
}