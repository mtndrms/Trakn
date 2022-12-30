package app.trakn.trakn.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.trakn.trakn.R
import app.trakn.trakn.adapters.MarketCryptocurrencyRecyclerViewAdapter
import app.trakn.trakn.api.ApiClient
import app.trakn.trakn.api.services.CryptoService
import app.trakn.trakn.models.responses.Coin
import app.trakn.trakn.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarketsFragment : Fragment() {

    private lateinit var cryptoService: CryptoService
    private lateinit var tickerList: Coin
    private lateinit var marketCryptocurrencyRecyclerViewAdapter: MarketCryptocurrencyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val topBar: RelativeLayout = requireActivity().findViewById(R.id.topBar)
        topBar.setBackgroundColor(
            ContextCompat.getColor(requireActivity(), R.color.markets)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_markets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiClient = ApiClient(Constants.CRYPTOCURRENCY_API_URL)

        val header: LinearLayout = view.findViewById(R.id.header)
        val horizontalScrollView: LinearLayout = view.findViewById(R.id.categories)
        val rvTickers: RecyclerView = view.findViewById(R.id.rvTickers)

        fun getAndDisplayData() {
            cryptoService = apiClient.retrofit!!.create(CryptoService::class.java)

            val tickers = cryptoService.getMarketData("USD", 0, 2000)
            tickers.enqueue(object : Callback<Coin> {
                override fun onResponse(
                    call: Call<Coin>,
                    response: Response<Coin>
                ) {
                    if (response.isSuccessful) {
                        tickerList = (response.body() as Coin)
                        marketCryptocurrencyRecyclerViewAdapter =
                            MarketCryptocurrencyRecyclerViewAdapter(tickerList.coins)
                        rvTickers.adapter = marketCryptocurrencyRecyclerViewAdapter
                        rvTickers.layoutManager = LinearLayoutManager(requireContext())
                    }
                }

                override fun onFailure(call: Call<Coin>, t: Throwable) {
                    println(t.message.toString())
                }
            })
        }

        for (item in 0 until horizontalScrollView.childCount) {
            val button = horizontalScrollView.getChildAt(item) as AppCompatButton
            button.setOnClickListener {
                button.setTextAppearance(context, R.style.HorizontalScrollView_SelectedOption)
                when (item) {
                    0 -> println("FAVORITES")
                    1 -> println("STOCKS")
                    2 -> getAndDisplayData()
                    3 -> println("FUTURES")
                    4 -> println("FIAT")
                    5 -> println("INDICES")
                }
                for (i in 0 until horizontalScrollView.childCount) {
                    if (i != horizontalScrollView.indexOfChild(button)) {
                        (horizontalScrollView.getChildAt(i) as AppCompatButton).setTextAppearance(
                            context,
                            R.style.HorizontalScrollView_UnselectedOption
                        )
                    }
                }
            }
        }

//        var state = 0
//        rvTickers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//
//                state = newState
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                if (dy > 0 && (state == 0 || state == 2)) {
//                    TopBarHelper.hideToolbar(header)
//                } else if (dy < -100) {
//                    TopBarHelper.showToolbar(header)
//                }
//            }
//        })
    }
}