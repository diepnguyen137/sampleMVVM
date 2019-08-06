package com.skg.mybook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottom_navi_view.setOnNavigationItemSelectedListener { p0 ->
            when(p0.itemId){
                R.id.nav_home -> TODO()
                R.id.nav_save -> TODO()
                R.id.nav_profile -> TODO()
            }
            true
        }
    }
}
