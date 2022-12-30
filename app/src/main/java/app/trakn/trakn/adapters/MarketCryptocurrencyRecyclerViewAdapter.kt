package app.trakn.trakn.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.trakn.trakn.R
import app.trakn.trakn.models.Cryptocurrency
import app.trakn.trakn.utils.Helper.format
import com.squareup.picasso.Picasso

class MarketCryptocurrencyRecyclerViewAdapter(private val data: List<Cryptocurrency>) :
    RecyclerView.Adapter<MarketCryptocurrencyRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val symbol: TextView
        val rank: TextView
        val price: TextView
        val change: TextView
        val image: ImageView

        init {
            name = view.findViewById(R.id.name)
            symbol = view.findViewById(R.id.symbol)
            rank = view.findViewById(R.id.ranking)
            price = view.findViewById(R.id.price)
            change = view.findViewById(R.id.change)
            image = view.findViewById(R.id.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_market_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            name.text = data[position].name
            symbol.text = data[position].symbol
            rank.text = data[position].rank.toString()
            price.text = data[position].price.format(2).toString()
            change.text = data[position].priceChange1d.toString()
            Picasso.get().load(data[position].icon).into(image)
        }
    }

    override fun getItemCount(): Int = data.size
}