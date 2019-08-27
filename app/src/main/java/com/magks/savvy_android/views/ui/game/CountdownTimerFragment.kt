package com.magks.savvy_android.views.ui.game

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.magks.savvy_android.viewmodels.CountdownTimerViewModel
import com.magks.savvy_android.R


class CountdownTimerFragment : Fragment() {

    companion object {
        fun newInstance() = CountdownTimerFragment()
    }

    private lateinit var viewModel: CountdownTimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.countdown_timer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(CountdownTimerViewModel::class.java)
            observeInput(viewModel)
        }
        // TODO: Use the ViewModel
    }

    private fun observeInput(viewModel: CountdownTimerViewModel) {
        Log.d(this.javaClass.simpleName, "observing playerlist")
        viewModel.secondsToLiveGame.observe(this, Observer {
            it?.let {
                // do something with input
                Log.d(this.javaClass.simpleName, CountdownTimerFragment.toString())
            }
        })
    }

}
