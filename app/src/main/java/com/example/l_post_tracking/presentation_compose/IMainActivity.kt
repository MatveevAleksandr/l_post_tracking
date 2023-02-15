package com.example.l_post_tracking.presentation_compose

import androidx.lifecycle.LiveData
import com.example.l_post_tracking.model.MainScreenState

sealed interface IMainActivity {
    interface IFindByNumOrTrackFragment : IMainActivity {
        fun findByOrderOrTrackNumClick(orderOrTrackNum: String)
    }

    interface IFindByPhoneFragment : IMainActivity {
        fun findByPhoneNumClick(orderOrTrackNum: String, phoneNum: String)
    }

    interface ISearchResultFragment : IMainActivity {
        fun addressClick(address: String)
    }

    fun getMainActivityLiveDataState(): LiveData<MainScreenState>
}