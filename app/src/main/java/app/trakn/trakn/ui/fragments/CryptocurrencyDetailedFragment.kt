package app.trakn.trakn.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import app.trakn.trakn.R
import app.trakn.trakn.utils.TopBarHelper

class CryptocurrencyDetailedFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val topBar: RelativeLayout = requireActivity().findViewById(R.id.topBar)
        TopBarHelper.changeTopBarButtons(topBar)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cryptocurrency_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireArguments().getString("id", "null")
    }

    override fun onDestroy() {
        super.onDestroy()

        val topBar: RelativeLayout = requireActivity().findViewById(R.id.topBar)
        TopBarHelper.changeTopBarButtonsToDefault(topBar)
    }
}