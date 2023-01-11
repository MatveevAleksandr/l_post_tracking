package com.example.l_post_tracking.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.l_post_tracking.R
import com.example.l_post_tracking.state.MainActivityFragmentState
import ru.lpost.domain.useCase.CallCCUseCase
import ru.lpost.domain.useCase.NewFindUseCase

class MainActivity : AppCompatActivity() {

    private val vm = MainViewModel(CallCCUseCase(), NewFindUseCase())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnNewFind).setOnClickListener {
            vm.setMainActivityState(MainActivityFragmentState.FIND_BY_NUM_OR_TRACK)
        }

        vm.getMainActivityFragmentState().observe(this){ state ->
            when (state){
                MainActivityFragmentState.FIND_BY_NUM_OR_TRACK -> supportFragmentManager.beginTransaction().replace(R.id.MainLayoutCenter, FindByNumOrTrackFragment()).commitNow()
                MainActivityFragmentState.FIND_BY_PHONE -> supportFragmentManager.beginTransaction().replace(R.id.MainLayoutCenter, FindByPhoneFragment()).commitNow()
                MainActivityFragmentState.WAITING -> supportFragmentManager.beginTransaction().replace(R.id.MainLayoutCenter, WaitingFragment()).commitNow()
                MainActivityFragmentState.RESULT -> supportFragmentManager.beginTransaction().replace(R.id.MainLayoutCenter, SearchResultFragment()).commitNow()
                else -> {}
            }
        }

        vm.setMainActivityState(MainActivityFragmentState.FIND_BY_NUM_OR_TRACK)
    }
}




