package com.magks.savvy_android.views.ui.topbar

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import com.magks.savvy_android.R
import com.magks.savvy_android.viewmodels.SavvyToolbarViewModel
import com.magks.savvy_android.views.ui.bind

class SavvyToolbarView(
                       private val activity: AppCompatActivity,
                       context: Context,
                       private val navController: NavController
        ) : View(context){

    //val actionBar by bind<Toolbar>(R.id.game_dashboard_toolbar)
    val actionBar by bind<Toolbar>(R.id.game_dashboard_toolbar, activity)
    val profileIconImageView by bind<ImageView>(R.id.profileIconImageView, activity)
    val savvyLogoImageView by bind<ImageView>(R.id.savvyLogoActiveImageView, activity)
    val chatIconImageView by bind<ImageView>(R.id.chatIconImageView, activity)
    private val toolbarViewModel: SavvyToolbarViewModel

    init {
        Log.d("SavvyToolbarView", "constructor")
        activity.setSupportActionBar(actionBar)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)
        // Viewmodel: toolbar
        toolbarViewModel = ViewModelProviders.of(activity).get(SavvyToolbarViewModel::class.java)
        // Toolbar navigation: let users navigate to profile activity
        setOnClickListeners()
    }

    fun setOnClickListeners() {
        profileIconImageView.setOnClickListener{navController.navigate(R.id.action_global_profileFragment)}
        savvyLogoImageView.setOnClickListener{navController.navigate(R.id.action_global_gameDashboardFragment)}
        chatIconImageView.setOnClickListener{navController.navigate(R.id.action_global_matchesChatFragment)}
    }

    fun disableButton(toolbarIconView: View){
        toolbarIconView.setOnClickListener(null)
    }

    companion object {
        fun profileButtonOnClick(navController: NavController) {
            navController.navigate(R.id.action_global_profileFragment)
        }

        fun savvyButtonOnClick(navController: NavController){
            navController.navigate(R.id.action_global_gameDashboardFragment)
        }

        fun chatButtonOnClick(navController: NavController) {
            navController.navigate(R.id.action_global_matchesChatFragment)
        }
    }
}
