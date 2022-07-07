package app.trakn.trakn.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.trakn.trakn.R
import app.trakn.trakn.models.News

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
    }

    override fun getItemCount(): Int {
        return news.size
    }
}