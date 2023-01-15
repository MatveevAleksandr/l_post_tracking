package com.example.l_post_tracking.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.l_post_tracking.R
import com.example.l_post_tracking.data.repository.OrderRepositoryImpl
import com.example.l_post_tracking.data.storage.APIOrderStorageImpl
import com.example.l_post_tracking.model.MainActivityFragment
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase
import com.example.l_post_tracking.viewmodel.MainViewModel
import com.example.l_post_tracking.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val callCCUseCase = CallCCUseCase(this)
    private val apiOrderStorage = APIOrderStorageImpl()
    private val orderRepository = OrderRepositoryImpl(storage =  apiOrderStorage)
    private val findByOrderOrTrackNumUseCase = FindByOrderOrTrackNumUseCase(orderRepository = orderRepository)
    private val findByPhoneNumUseCase = FindByPhoneNumUseCase(orderRepository = orderRepository)
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(
            this, MainViewModelFactory(
                callCCUseCase = callCCUseCase,
                findByOrderOrTrackNumUseCase = findByOrderOrTrackNumUseCase,
                findByPhoneNumUseCase = findByPhoneNumUseCase
            )
        )[MainViewModel::class.java]

        val btnNewFind = findViewById<Button>(R.id.btnNewFind)
        btnNewFind.setOnClickListener { vm.newSearchClick() }

        findViewById<TextView>(R.id.tvHeadCCPhone).setOnClickListener { vm.callCCClick() }

        vm.getMainActivityFragmentState().observe(this) { mainActivityFragmentState ->
            btnNewFind.visibility = View.GONE
            when (mainActivityFragmentState.mainActivityFragment) {
                MainActivityFragment.FIND_BY_NUM_OR_TRACK -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainLayoutCenter, FindByNumOrTrackFragment(vm = vm))
                        .commitNow()
                }
                MainActivityFragment.FIND_BY_PHONE -> {
                    btnNewFind.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainLayoutCenter, FindByPhoneFragment(vm = vm)).commitNow()
                }
                MainActivityFragment.WAITING -> {
                    btnNewFind.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainLayoutCenter, WaitingFragment()).commitNow()
                }
                MainActivityFragment.RESULT -> {
                    btnNewFind.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainLayoutCenter, SearchResultFragment()).commitNow()
                }
            }
        }
    }
}




