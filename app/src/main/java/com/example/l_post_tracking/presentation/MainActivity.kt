package com.example.l_post_tracking.presentation

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.l_post_tracking.R
import com.example.l_post_tracking.state.MainActivityFragmentState
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.NewFindUseCase


class MainActivity : AppCompatActivity() {

    private val vm = MainViewModel(Application(), CallCCUseCase(), NewFindUseCase())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNewFind = findViewById<Button>(R.id.btnNewFind)
        btnNewFind.visibility = View.GONE
        btnNewFind.setOnClickListener {

        }

        findViewById<TextView>(R.id.tvHeadCCPhone).setOnClickListener { vm.callCC() }

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




