package app.trakn.trakn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import app.trakn.trakn.utils.NavigationHelper.changeColorOfChildViews
import app.trakn.trakn.utils.NavigationHelper.defaultColorsForChildViews
import app.trakn.trakn.utils.NavigationHelper.BottomNavigationLabelChildViews

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topBar: RelativeLayout = findViewById(R.id.topBar)
        val btnPortfolio: LinearLayout = findViewById(R.id.portfolioLabel)
        val btnMarkets: LinearLayout = findViewById(R.id.marketsLabel)
        val btnNewsfeed: LinearLayout = findViewById(R.id.newsfeedLabel)

        var state = "portfolioLabel"

        fun changeFragment(fragment: Fragment) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.run {
                replace(R.id.fragmentContainer, fragment)
                addToBackStack(null)
                commit()
            }
        }

        listOf(btnPortfolio, btnMarkets, btnNewsfeed).forEach { item ->
            item.setOnClickListener {
                mutableListOf(btnPortfolio, btnMarkets, btnNewsfeed).let { mutableList ->
                    mutableList.remove(item)
                    defaultColorsForChildViews(mutableList, applicationContext)
                }

                changeColorOfChildViews(
                    BottomNavigationLabelChildViews(
                        firstChild = item.getChildAt(0) as ImageView,
                        secondChild = item.getChildAt(1) as TextView
                    ), applicationContext
                )

                if (state != resources.getResourceEntryName(it.id)) {
                    when (resources.getResourceEntryName(it.id)) {
                        "portfolioLabel" -> {
                            topBar.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.black))
                        }
                        "marketsLabel" -> {
                            topBar.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.markets))
                        }
                        "newsfeedLabel" -> {
                            topBar.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.newsfeed))
                            changeFragment(NewsfeedFragment())
                        }
                    }
                }
                state = resources.getResourceEntryName(it.id)
            }
        }
    }
}

