package com.magks.savvy_android.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class SavvyToolbarViewModel :ViewModel() {
    private val _onProfileActivity =  MutableLiveData<Boolean>()
    val onProfileActivity : LiveData<Boolean> = _onProfileActivity

    init {
        _onProfileActivity.value = false
    }

    fun isLeavingProfileActivity() {
        _onProfileActivity.value = false
    }

    fun isEnteringProfileActivity() {
        _onProfileActivity.value = true
    }
}
