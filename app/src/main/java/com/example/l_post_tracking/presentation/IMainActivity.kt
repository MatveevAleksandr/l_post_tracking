package com.example.l_post_tracking.presentation

import androidx.lifecycle.MutableLiveData
import com.example.l_post_tracking.model.MainActivityState

interface IMainActivity {
    fun getMainActivityState(): MutableLiveData<MainActivityState>
    fun findByOrderOrTrackNumClick(orderOrTrackNum: String)
    fun findByPhoneNumClick(orderOrTrackNum: String, phoneNum: String)
    fun addressClick(address: String)
}
