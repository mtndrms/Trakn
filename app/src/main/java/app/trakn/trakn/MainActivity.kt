package app.trakn.trakn

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.trakn.trakn.utils.BottomNavigationHelper.BottomNavigationLabelChildViews
import app.trakn.trakn.utils.BottomNavigationHelper.changeColorOfChildViews
import app.trakn.trakn.utils.BottomNavigationHelper.defaultColorsForChildViews

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPortfolio: LinearLayout = findViewById(R.id.portfolioLabel)
        val btnMarkets: LinearLayout = findViewById(R.id.marketsLabel)
        val btnNewsfeed: LinearLayout = findViewById(R.id.newsfeedLabel)
        val btnMenu: ImageView = findViewById(R.id.btnMenu)
        val btnNotifications: ImageView = findViewById(R.id.btnNotification)
        val btnBack: ImageView = findViewById(R.id.btnBack)
        val btnShare: ImageView = findViewById(R.id.btnShare)

        var state = "portfolioLabel"

        fun changeFragment(fragment: Fragment) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.run {
                replace(R.id.fragmentContainer, fragment)
                addToBackStack(null)
                commit()
            }
        }

        btnMenu.setOnClickListener {
            val f: Fragment? = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
            if (f is NewsDetailedFragment) {
                println("YES")
            }
        }

        btnBack.setOnClickListener {
            supportFragmentManager.popBackStack()
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
                        }
                        "marketsLabel" -> {
                            changeFragment(MarketsFragment())
                        }
                        "newsfeedLabel" -> {
                            changeFragment(NewsfeedFragment())
                        }
                    }
                }
                state = resources.getResourceEntryName(it.id)
            }
        }
    }
}

