package com.magks.savvy_android.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class GameDashboardViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val playerList = MutableLiveData<List<Int>>() //Change to List<Players> or similar metaphor
}
