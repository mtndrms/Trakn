package app.trakn.trakn.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.trakn.trakn.R
import app.trakn.trakn.adapters.NewsfeedRecyclerViewAdapter
import app.trakn.trakn.api.ApiClient
import app.trakn.trakn.api.services.NewsfeedService
import app.trakn.trakn.models.responses.News
import app.trakn.trakn.utils.Constants
import app.trakn.trakn.utils.TopBarHelper.hideToolbar
import app.trakn.trakn.utils.TopBarHelper.showToolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsfeedFragment : Fragment() {
    private lateinit var newsfeedService: NewsfeedService
    private lateinit var newsList: News
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

        val apiClient = ApiClient(Constants.CRYPTOCURRENCY_API_URL)

        val rvNewsList: RecyclerView = view.findViewById(R.id.newsList)
        val header: LinearLayout = view.findViewById(R.id.header)

        newsfeedService = apiClient.retrofit!!.create(NewsfeedService::class.java)

        val news = newsfeedService.getAllCryptocurrencyNews(0, 3, null, null)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    newsList = (response.body() as News)
                    newsfeedRecyclerViewAdapter = NewsfeedRecyclerViewAdapter(newsList.news)
                    rvNewsList.adapter = newsfeedRecyclerViewAdapter
                    rvNewsList.layoutManager = LinearLayoutManager(requireContext())
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                println(t.message.toString())
            }
        })

        var state = 0
        rvNewsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                state = newState
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0 && (state == 0 || state == 2)) {
                    hideToolbar(header)
                } else if (dy < -100) {
                    showToolbar(header)
                }
            }
        })
    }
}