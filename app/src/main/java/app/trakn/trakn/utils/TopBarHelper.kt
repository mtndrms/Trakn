package app.trakn.trakn.utils

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout

object TopBarHelper {

    fun changeTopBarButtons(topBar: RelativeLayout) {
        val btnMenu: ImageView = topBar.getChildAt(0) as ImageView
        val btnNotifications: ImageView = topBar.getChildAt(1) as ImageView
        val btnBack: ImageView = topBar.getChildAt(2) as ImageView
        val btnShare: ImageView = topBar.getChildAt(3) as ImageView

        btnMenu.visibility = View.INVISIBLE
        btnNotifications.visibility = View.INVISIBLE
        btnBack.visibility = View.VISIBLE
        btnShare.visibility = View.VISIBLE
    }

    fun changeTopBarButtonsToDefault(topBar: RelativeLayout) {
        val btnMenu: ImageView = topBar.getChildAt(0) as ImageView
        val btnNotifications: ImageView = topBar.getChildAt(1) as ImageView
        val btnBack: ImageView = topBar.getChildAt(2) as ImageView
        val btnShare: ImageView = topBar.getChildAt(3) as ImageView

        btnMenu.visibility = View.VISIBLE
        btnNotifications.visibility = View.VISIBLE
        btnBack.visibility = View.INVISIBLE
        btnShare.visibility = View.INVISIBLE
    }
}