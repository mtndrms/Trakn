package app.trakn.trakn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsDetailedFragment : Fragment() {
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

        val title = requireArguments().getString("title", "null")
        val details = requireArguments().getString("details", "null")
        val date = requireArguments().getString("date", "null")
        val source = requireArguments().getString("source", "null")

        tvTitle.text = title
        tvDetails.text = details.times(10)
        "$source at $date".also { tvSourceAndDate.text = it }
    }
}

fun String.times(times: Int): CharSequence {
    var result = this
    for (i in 0..times) {
        result += this
    }
    return result
}
