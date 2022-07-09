package app.trakn.trakn.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import app.trakn.trakn.NewsDetailedFragment
import app.trakn.trakn.R
import app.trakn.trakn.models.News
import app.trakn.trakn.utils.TopBarHelper
import com.squareup.picasso.Picasso


class NewsfeedRecyclerViewAdapter(private val news: MutableList<News>) :
    RecyclerView.Adapter<NewsfeedRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val source: TextView
        val date: TextView
        val image: ImageView

        init {
            title = view.findViewById(R.id.title)
            source = view.findViewById(R.id.source)
            date = view.findViewById(R.id.date)
            image = view.findViewById(R.id.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.newsfeed_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = news[position].title
        holder.source.text = news[position].albumId.toString()
        holder.date.text = news[position].id.toString()
        Picasso.get()
            .load(news[position].thumbnailUrl)
            .resize(75, 75)
            .centerCrop()
            .into(holder.image)
        showDetails(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    private fun showDetails(view: View, position: Int) {
        view.setOnClickListener {
            val activity = view.context as AppCompatActivity
            val bundle = Bundle()
            bundle.putString("title", news[position].title)
            bundle.putString("details", news[position].title)
            bundle.putString("source", news[position].albumId.toString())
            bundle.putString("date", news[position].id.toString())
            bundle.putString("url", news[position].url)
            val fragment: Fragment = NewsDetailedFragment()
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, "DETAILS_FRAGMENT").addToBackStack(null)
                .commit()
        }
    }
}