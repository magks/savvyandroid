package com.magks.savvy_android.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class CountdownTimerViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val secondsToLiveGame = MutableLiveData<Int>() //Change to List<Players> or similar metaphor
}
