package com.magks.savvy_android.Views


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.opengl.Visibility
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener

import androidx.appcompat.widget.Toolbar
import android.view.View

import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.magks.savvy_android.R
import com.magks.savvy_android.ViewModels.GameDashboardViewModel
import com.magks.savvy_android.ViewModels.ToolbarViewModel
import com.magks.savvy_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarViewModel: ToolbarViewModel
    private lateinit var gameDashboardViewModel : GameDashboardViewModel
    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dashboard_help -> {
                textMessage.setText(R.string.dashboard_help)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
    /*
    fun startProfileActivity(view: View){
        val goToProfile = Intent(this, ProfileActivity::class.java)
        toolbarViewModel.isEnteringProfileActivity()

        val toolbarProfileIcon = findViewById<ImageView>(R.id.iconProfileImageView)
        toolbarProfileIcon.visibility = View.GONE
        toolbarProfileIcon.setOnClickListener(null)
        toolbarProfileIcon.isClickable = false
        startActivity(goToProfile)
    }
    */

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
        val toolbar = findViewById<Toolbar>(R.id.game_dashboard_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        // Game dashboard fragment
        supportFragmentManager.beginTransaction().add(
            R.id.game_dashboard_frame_layout,
            GameDashboardFragment()
        ).commit()

        // Viewmodel: main game waiting dashboard

        gameDashboardViewModel = ViewModelProviders.of(this).get(GameDashboardViewModel::class.java)
        gameDashboardViewModel.playerList.observe(this, Observer {
            it?.let {
                // do some thing with the list
            }
        })

        // Viewmodel: toolbar
        toolbarViewModel = ViewModelProviders.of(this).get(ToolbarViewModel::class.java)

    }


    override fun onStart() {
        super.onStart()
        // Toolbar navigation: let users navigate to profile activity
        val toolbarProfileIcon = findViewById<ImageView>(R.id.iconProfileImageView)
        toolbarProfileIcon.isClickable = true
        toolbarProfileIcon.visibility = View.VISIBLE
        toolbarProfileIcon.setOnClickListener{
            val goToProfile = Intent(this, ProfileActivity::class.java)
            toolbarViewModel.isEnteringProfileActivity()
            toolbarProfileIcon.isClickable = false // prevent fast double-tap from opening multiple new activities
            startActivity(goToProfile)
        }
    }



    /**
     * @param toolbar toolbar
     * @param resId 图片资源id
     * @return 被添加的ImageView
     */
    fun addButtonToToolbar(toolbar: Toolbar?, resId: Int, width:Int, height: Int, gravity: Int): ImageView? {
        if (toolbar == null) {
            Toast.makeText(this, "Toolbar is null!", Toast.LENGTH_LONG).show()
            return null
        }
        val context = toolbar.context
        val img = ImageView(
            context
        )
        img.setImageResource(resId)
        img.scaleType = ImageView.ScaleType.CENTER_INSIDE

        val params = Toolbar.LayoutParams(width, height, gravity)
        img.setLayoutParams(params)
        toolbar.addView(img)
        return img
    }

}
