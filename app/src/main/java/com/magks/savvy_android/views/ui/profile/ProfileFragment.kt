package com.magks.savvy_android.views.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magks.savvy_android.viewmodels.ProfileViewModel
import com.magks.savvy_android.R

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

/*

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
import com.magks.savvy_android.viewmodels.SavvyToolbarViewModel
import com.magks.savvy_android.views.ui.MainActivity

class ProfileActivity : AppCompatActivity() {


    private lateinit var toolbarViewModel: SavvyToolbarViewModel

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
        toolbarViewModel = ViewModelProviders.of(this).get(SavvyToolbarViewModel::class.java)

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
 */
