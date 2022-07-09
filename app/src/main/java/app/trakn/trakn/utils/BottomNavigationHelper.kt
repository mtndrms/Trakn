package app.trakn.trakn.utils

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import app.trakn.trakn.R

object BottomNavigationHelper {

    data class BottomNavigationLabelChildViews(
        val firstChild: ImageView,
        val secondChild: TextView,
    )

    fun changeColorOfChildViews(childViews: BottomNavigationLabelChildViews, context: Context) {
        childViews.firstChild.setColorFilter(ContextCompat.getColor(context, R.color.markets))
        childViews.secondChild.setTextColor(ContextCompat.getColor(context, R.color.markets))
    }

    fun defaultColorsForChildViews(list: MutableList<LinearLayout>, context: Context) {
        list.forEach {
            val firstChild = it.getChildAt(0) as ImageView
            val secondChild = it.getChildAt(1) as TextView
            firstChild.setColorFilter(ContextCompat.getColor(context, R.color.black))
            secondChild.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }
}