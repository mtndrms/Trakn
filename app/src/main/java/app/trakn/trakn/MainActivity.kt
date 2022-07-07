package app.trakn.trakn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPortfolio: LinearLayout = findViewById(R.id.firstLabel)
        val btnMarkets: LinearLayout = findViewById(R.id.secondLabel)
        val btnNewsfeed: LinearLayout = findViewById(R.id.thirdLabel)

        fun changeFragment(fragment: Fragment) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.run {
                replace(R.id.fragmentContainer, fragment)
                addToBackStack(null)
                commit()
            }
        }

        btnNewsfeed.setOnClickListener {
            changeFragment(NewsfeedFragment())
        }
    }
}