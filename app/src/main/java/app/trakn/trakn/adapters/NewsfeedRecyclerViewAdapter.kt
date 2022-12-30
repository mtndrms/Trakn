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
import app.trakn.trakn.ui.fragments.NewsDetailedFragment
import app.trakn.trakn.R
import app.trakn.trakn.models.New
import app.trakn.trakn.models.responses.News
import com.squareup.picasso.Picasso

class NewsfeedRecyclerViewAdapter(private val news: List<New>) :
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
            .inflate(R.layout.recyclerview_newsfeed_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = news[position].title
        holder.source.text = news[position].source
        holder.date.text = news[position].id.toString()
        Picasso.get()
            .load(news[position].imgURL)
            .resize(75, 75)
            .centerCrop()
            .into(holder.image)
        showDetails(holder.itemView, position)
    }

    override fun getItemCount(): Int = news.size

    private fun showDetails(view: View, position: Int) {
        view.setOnClickListener {
            val activity = view.context as AppCompatActivity
            val bundle = Bundle()
            bundle.putString("title", news[position].title)
            bundle.putString("details", news[position].description)
            bundle.putString("source", news[position].source)
            bundle.putString("date", news[position].feedDate.toString())
            bundle.putString("url", news[position].imgURL)
            val fragment: Fragment = NewsDetailedFragment()
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment, "NEWS_DETAILS_FRAGMENT")
                .addToBackStack(null)
                .commit()
        }
    }
}