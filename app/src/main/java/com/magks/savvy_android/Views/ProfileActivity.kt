package com.magks.savvy_android.Views

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.magks.savvy_android.R
import com.magks.savvy_android.ViewModels.ToolbarViewModel

class ProfileActivity : AppCompatActivity() {


    private lateinit var toolbarViewModel: ToolbarViewModel

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.game_dashboard_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // View Model: toolbar
        toolbarViewModel = ViewModelProviders.of(this).get(ToolbarViewModel::class.java)

        // Display
        setContentView(R.layout.activity_profile)


    }

    override fun onStart() {
        super.onStart()
        // Replace profile icon with game icon
        val toolbarProfileIcon = findViewById<ImageView>(R.id.iconProfileImageView)
        toolbarProfileIcon.visibility = View.GONE
        toolbarProfileIcon.setOnClickListener(null)
        toolbarProfileIcon.isClickable = false
        // Whatever icon this is, the point is to go back to the game lobby (game dashboard, i.e. main activity)
        val toolbarGameIcon = findViewById<ImageView>(R.id.iconOnProfileBackArrowImageView)
        toolbarGameIcon?.visibility = View.VISIBLE
        toolbarGameIcon?.isClickable = true
        toolbarGameIcon?.setOnClickListener {
            val goToMainDashboard = Intent(this, MainActivity::class.java)
            toolbarGameIcon?.isClickable = false
            startActivity(goToMainDashboard)
        }
    }

    override fun onStop() {
        super.onStop()
        // Update toolbar: remove game dashboard button to make room for profile button
        toolbarViewModel.isLeavingProfileActivity()
        val toolbarGameIcon = findViewById<ImageView>(R.id.iconOnProfileBackArrowImageView)
        toolbarGameIcon.visibility = View.GONE
        toolbarGameIcon.setOnClickListener(null)
    }



}
