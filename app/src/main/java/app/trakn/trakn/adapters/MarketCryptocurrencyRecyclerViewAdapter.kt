package app.trakn.trakn.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import app.trakn.trakn.R
import app.trakn.trakn.models.Cryptocurrency
import app.trakn.trakn.ui.fragments.CryptocurrencyDetailedFragment
import app.trakn.trakn.ui.fragments.NewsDetailedFragment
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

        showDetails(holder.itemView, position)
    }

    override fun getItemCount(): Int = data.size

    private fun showDetails(view: View, position: Int) {
        view.setOnClickListener {
            val activity = view.context as AppCompatActivity
            val fragment: Fragment = CryptocurrencyDetailedFragment()
            val bundle = Bundle()

            bundle.putString("id", data[position].id)
            fragment.arguments = bundle

            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, "CRYPTOCURRENCY_DETAILS_FRAGMENT")
                .addToBackStack(null).commit()
        }
    }
}