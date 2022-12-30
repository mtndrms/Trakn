package app.trakn.trakn.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import app.trakn.trakn.R
import app.trakn.trakn.utils.Helper.times
import app.trakn.trakn.utils.TopBarHelper
import com.squareup.picasso.Picasso

class NewsDetailedFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val topBar: RelativeLayout = requireActivity().findViewById(R.id.topBar)
        TopBarHelper.changeTopBarButtons(topBar)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle: TextView = view.findViewById(R.id.title)
        val tvDetails: TextView = view.findViewById(R.id.details)
        val tvSourceAndDate: TextView = view.findViewById(R.id.sourceAndDate)
        val ivImage: ImageView = view.findViewById(R.id.image)

        val title = requireArguments().getString("title", "null")
        val details = requireArguments().getString("details", "null")
        val date = requireArguments().getString("date", "null")
        val source = requireArguments().getString("source", "null")
        val url = requireArguments().getString("url", "null")

        tvTitle.text = title
        tvDetails.text = details
        "$source at $date".also { tvSourceAndDate.text = it }
        Picasso.get()
            .load(url)
            .resize(340, 340)
            .centerCrop()
            .into(ivImage)
    }

    override fun onDestroy() {
        super.onDestroy()

        val topBar: RelativeLayout = requireActivity().findViewById(R.id.topBar)
        TopBarHelper.changeTopBarButtonsToDefault(topBar)
    }
}
