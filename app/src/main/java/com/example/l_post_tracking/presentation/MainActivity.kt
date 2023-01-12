package com.example.l_post_tracking.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.l_post_tracking.R
import com.example.l_post_tracking.state.MainActivityFragmentState
import com.example.l_post_tracking.usecase.CallCCUseCase

class MainActivity : AppCompatActivity() {

    private val callCCUseCase = CallCCUseCase(this)
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this, MainViewModelFactory(callCCUseCase = callCCUseCase))[MainViewModel::class.java]

        val btnNewFind = findViewById<Button>(R.id.btnNewFind)
        btnNewFind.setOnClickListener {
            vm.setMainActivityState(MainActivityFragmentState.FIND_BY_NUM_OR_TRACK)
        }

        findViewById<TextView>(R.id.tvHeadCCPhone).setOnClickListener { vm.callCC() }

        vm.getMainActivityFragmentState().observe(this){ state ->
            btnNewFind.visibility = View.GONE
            when (state){
                MainActivityFragmentState.FIND_BY_NUM_OR_TRACK -> {
                    supportFragmentManager.beginTransaction().replace(R.id.MainLayoutCenter, FindByNumOrTrackFragment()).commitNow()
                }
                MainActivityFragmentState.FIND_BY_PHONE -> {
                    btnNewFind.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction().replace(R.id.MainLayoutCenter, FindByPhoneFragment()).commitNow()
                }
                MainActivityFragmentState.WAITING -> {
                    supportFragmentManager.beginTransaction().replace(R.id.MainLayoutCenter, WaitingFragment()).commitNow()
                }
                MainActivityFragmentState.RESULT -> {
                    btnNewFind.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction().replace(R.id.MainLayoutCenter, SearchResultFragment()).commitNow()
                }
                else -> {}
            }
        }
    }
}




