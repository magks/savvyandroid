package com.magks.savvy_android.views.ui

import android.content.Context
import android.text.TextUtils.replace
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.magks.savvy_android.R
import com.magks.savvy_android.viewmodels.SavvyToolbarViewModel
//import com.magks.savvy_android.views.ui.bind
import com.magks.savvy_android.views.ui.game.GameDashboardFragment
import com.magks.savvy_android.views.ui.matches.MatchesChatFragment
import com.magks.savvy_android.views.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_dashboard.view.*
import java.lang.reflect.Constructor
import kotlin.reflect.jvm.javaConstructor
import kotlin.reflect.jvm.reflect

class SavvyToolbarView(context: Context, private val activity: AppCompatActivity, private val supportFragmentManager: FragmentManager) : View(context){

    //val actionBar by bind<Toolbar>(R.id.game_dashboard_toolbar)
    val actionBar by bind<Toolbar>(R.id.game_dashboard_toolbar, activity)
    val profileIconImageView by bind<ImageView>(R.id.profileIconImageView, activity)
    val savvyLogoImageView by bind<ImageView>(R.id.savvyLogoActiveImageView, activity)
    val chatIconImageView by bind<ImageView>(R.id.chatIconImageView, activity)
    val profileFragmentTag: String
    val gameDashboardFragmentTag: String
    val matchesChatFragmentTag: String
    val tagsList: List<String>

    private val toolbarViewModel: SavvyToolbarViewModel

    init {
        Log.d("SavvyToolbarView", "constructor")
        gameDashboardFragmentTag = resources.getString(R.string.game_dashboard_tag)
        profileFragmentTag = resources.getString(R.string.profile_fragment_tag)
        matchesChatFragmentTag = resources.getString(R.string.matches_chat_fragment_tag)
        tagsList = listOf(gameDashboardFragmentTag, profileFragmentTag, matchesChatFragmentTag)

        activity.setSupportActionBar(actionBar)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)
        // Viewmodel: toolbar
        toolbarViewModel = ViewModelProviders.of(activity).get(SavvyToolbarViewModel::class.java)

        // Toolbar navigation: let users navigate to profile activity
        setOnClickListeners()
        supportFragmentManager.addOnBackStackChangedListener {
            val backEntryCount = supportFragmentManager.backStackEntryCount
            if (backEntryCount < 0)
                return@addOnBackStackChangedListener
            if (backEntryCount == 0)
                return@addOnBackStackChangedListener


            for (s in tagsList)
                if (activity.fragment_container.findViewWithTag<View>(s) != null)
            activity.fragment_container.findViewWithTag<View>(gameDashboardFragmentTag)
            activity.fragment_container.findViewWithTag<View>(matchesChatFragmentTag)
            activity.fragment_container.findViewWithTag<View>(profileFragmentTag)
            supportFragmentManager.getBackStackEntryAt(backEntryCount-1)


        }
    }

    fun setOnClickListeners() {
        profileIconImageView.setOnClickListener {
            switchThenStopListening(::profileButtonOnClick, //activity, supportFragmentManager,
                profileFragmentTag, profileIconImageView
            )
        }
        savvyLogoImageView.setOnClickListener{
            switchThenStopListening(::savvyButtonOnClick, gameDashboardFragmentTag, savvyLogoImageView)
        }
        chatIconImageView.setOnClickListener{
            switchThenStopListening(::chatButtonOnClick, matchesChatFragmentTag, chatIconImageView)
        }
    }

    fun disableButton(toolbarIconView: View){
        toolbarIconView.setOnClickListener(null)
    }

    fun switchThenStopListening(onClick: (supportFragmentManager: FragmentManager, fragmentTag: String) -> Unit,
                                fragmentTag: String, view: View) {
        onClick(supportFragmentManager, fragmentTag)
        setOnClickListeners()
        disableButton(view)
    }



    companion object {
        fun profileButtonOnClick(supportFragmentManager: FragmentManager, profileFragmentTag : String){
            // Pop off fragments until the profile fragment and then remove that too (too avoid multiple)
            supportFragmentManager.popBackStack(profileFragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            // Put new profile fragment instance in fragment manager and place it on top of back button stack
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, ProfileFragment(), profileFragmentTag)
                addToBackStack(profileFragmentTag)
            }.commit()
        }

        fun savvyButtonOnClick(supportFragmentManager: FragmentManager, gameDashboardFragmentTag: String){
            // Present the game dashboard fragment
            SavvyViewUtils.presentFragment( supportFragmentManager, gameDashboardFragmentTag, GameDashboardFragment(), addBackStack = true)
            //SavvyViewUtils.presentFragment(supportFragmentManager, gameDashboardFragmentTag, ::GameDashboardFragment.javaConstructor as Constructor<Fragment>)
        }

        fun chatButtonOnClick(supportFragmentManager: FragmentManager, matchesChatFragmentTag: String){
            // Pop off fragments until the matchesChat fragment and then remove that too (too avoid multiple)
            supportFragmentManager.popBackStack(matchesChatFragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            // Put new matchesChat fragment instance in fragment manager and place it on top of back button stack
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, MatchesChatFragment(), matchesChatFragmentTag)
                addToBackStack(matchesChatFragmentTag)
            }.commit()


        }

    }



}
