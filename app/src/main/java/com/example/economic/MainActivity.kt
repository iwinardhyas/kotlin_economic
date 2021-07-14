package com.example.economic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.economic.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val homeFragment = HomeFragment()
        val ecoblogFragment = EcoBlogFragment()
        val cartFragment = ChartFragment()
        val meFragment = meFragment()

        makeCurrentfragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_home -> makeCurrentfragment(homeFragment)
                R.id.ic_eco_blog -> makeCurrentfragment(ecoblogFragment)
                R.id.ic_cart -> makeCurrentfragment(cartFragment)
                R.id.ic_me -> makeCurrentfragment(meFragment)
            }
            true
        }
    }


    private fun makeCurrentfragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }
}