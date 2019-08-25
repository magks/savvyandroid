package com.magks.savvy_android.views.ui.matches

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.magks.savvy_android.R
import com.magks.savvy_android.viewmodels.MatchesChatViewModel

class MatchesChatFragment : Fragment() {

    companion object {
        fun newInstance() = MatchesChatFragment()
    }

    private lateinit var viewModel: MatchesChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.matches_chat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MatchesChatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
