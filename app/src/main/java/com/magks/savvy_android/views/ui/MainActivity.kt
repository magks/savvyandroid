package com.magks.savvy_android.views.ui


import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener

import android.view.Window
import android.view.WindowManager
import androidx.navigation.findNavController
import com.magks.savvy_android.R
import com.magks.savvy_android.R.id.nav_host_fragment
import com.magks.savvy_android.databinding.ActivityMainBinding
import com.magks.savvy_android.views.ui.topbar.SavvyToolbarView

class MainActivity : AppCompatActivity() {
    private lateinit var mSavvyToolbar : SavvyToolbarView

    private val onNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dashboard_help -> {
                findNavController(nav_host_fragment).navigate(R.id.action_global_dashboardHelpFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Data binding
        // Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        //setContentView(R.layout.activity_main)
        // Hide default actionbar
       // supportActionBar?.hide()

        // Navigation

        // Bottom Nav

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        //Toolbar
        mSavvyToolbar = SavvyToolbarView(
            this,
            applicationContext,
            findNavController(nav_host_fragment)
        )
    }

}
