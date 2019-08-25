package com.magks.savvy_android.views.ui


import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener

import android.view.Window
import android.view.WindowManager
import com.magks.savvy_android.R
import com.magks.savvy_android.databinding.ActivityMainBinding
import com.magks.savvy_android.views.ui.game.GameDashboardFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mSavvyToolbar : SavvyToolbarView
    private lateinit var gameDashboardFragmentTag : String
    private lateinit var profileFragmentTag : String
    var mGameDashboardFragment: GameDashboardFragment? = null

    private val onNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dashboard_help -> {
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

        // Bottom Nav
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        //Toolbar
        mSavvyToolbar = SavvyToolbarView(applicationContext, this, supportFragmentManager)

        // Initialize fragment tags
        gameDashboardFragmentTag = resources.getString(R.string.game_dashboard_tag)
        profileFragmentTag = resources.getString(R.string.profile_fragment_tag)

        // Show game dashboard fragment
        mGameDashboardFragment = GameDashboardFragment()
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount < 0)
                finish()
        }
        SavvyViewUtils.presentFragment(supportFragmentManager, gameDashboardFragmentTag, mGameDashboardFragment!!, addBackStack = false)
        mSavvyToolbar.disableButton(mSavvyToolbar.savvyLogoImageView)
    }

}
