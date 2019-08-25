package com.magks.savvy_android.views.ui.game

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magks.savvy_android.R
import com.magks.savvy_android.viewmodels.GameDashboardViewModel


class GameDashboardFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = GameDashboardFragment()
    }

    private lateinit var gameDashboardViewModel: GameDashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_dashboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //val activity = activity?: return
        activity?.let {
            gameDashboardViewModel = ViewModelProviders.of(it).get(GameDashboardViewModel::class.java)
            observeInput(gameDashboardViewModel)
        }
        //viewModel = getViewModel<GameDashboardViewModel>(viewLifecycleOwner)//ViewModelProviders.of(this).get(GameDashboardViewModel::class.java)
        // TODO: Use the ViewModel
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
