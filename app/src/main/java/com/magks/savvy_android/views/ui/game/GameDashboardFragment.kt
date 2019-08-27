package com.magks.savvy_android.views.ui.game

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.magks.savvy_android.R
import com.magks.savvy_android.viewmodels.GameDashboardViewModel




class GameDashboardFragment : Fragment() {

    companion object {
        fun newInstance() = GameDashboardFragment()
    }

    private lateinit var gameDashboardViewModel: GameDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.magks.savvy_android.R.layout.game_dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //val activity = activity?: return
        activity?.let {
            Log.d(this.javaClass.simpleName, "let game dash fragment activity...")
            addCountdownTimerFragment()
            gameDashboardViewModel = ViewModelProviders.of(it).get(GameDashboardViewModel::class.java)
            observeInput(gameDashboardViewModel)
        }
        //viewModel = getViewModel<GameDashboardViewModel>(viewLifecycleOwner)//ViewModelProviders.of(this).get(GameDashboardViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun addCountdownTimerFragment() {
        val childFragMan = childFragmentManager
        val childFragTrans = childFragMan.beginTransaction()
        val fragB = CountdownTimerFragment()
        childFragTrans.replace(R.id.countdown_timer_frame, fragB, resources.getString(R.string.countdown_timer_fragment_tag))
        childFragTrans.commit()
    }

    // extensions
    private fun observeInput(gameDashboardViewModel: GameDashboardViewModel) {
        Log.d(this.javaClass.simpleName, "observing playerlist")
        gameDashboardViewModel.playerList.observe(this, Observer {
            it?.let {
                // do something with input
                Log.d(this.javaClass.simpleName, GameDashboardFragment.toString())
            }
        })
    }

}
