package com.example.l_post_tracking.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.l_post_tracking.R
import com.example.l_post_tracking.model.*
import com.example.l_post_tracking.repository.OrderRepositoryImpl
import com.example.l_post_tracking.storage.APIOrderStorageImpl
import com.example.l_post_tracking.usecase.CallCCUseCase
import com.example.l_post_tracking.usecase.FindAddressInMapUseCase
import com.example.l_post_tracking.usecase.FindByOrderOrTrackNumUseCase
import com.example.l_post_tracking.usecase.FindByPhoneNumUseCase
import com.example.l_post_tracking.viewmodel.MainViewModel
import com.example.l_post_tracking.viewmodel.MainViewModelFactory

class MainActivityImpl : AppCompatActivity(), IMainActivity, IMainActivity_A {
    private val callCCUseCase = CallCCUseCase(context = this)
    private val findAddressInMapUseCase = FindAddressInMapUseCase(context = this)
    private val apiOrderStorage = APIOrderStorageImpl()
    private val orderRepository = OrderRepositoryImpl(storage = apiOrderStorage)
    private val findByOrderOrTrackNumUseCase =
        FindByOrderOrTrackNumUseCase(orderRepository = orderRepository)
    private val findByPhoneNumUseCase = FindByPhoneNumUseCase(orderRepository = orderRepository)
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(
            this, MainViewModelFactory(
                callCCUseCase = callCCUseCase,
                findAddressInMapUseCase = findAddressInMapUseCase,
                findByOrderOrTrackNumUseCase = findByOrderOrTrackNumUseCase,
                findByPhoneNumUseCase = findByPhoneNumUseCase
            )
        )[MainViewModel::class.java]

        val btnNewFind = findViewById<Button>(R.id.btnNewFind)
        btnNewFind.setOnClickListener { vm.newSearchClick() }

        findViewById<TextView>(R.id.tvHeadCCPhone).setOnClickListener { vm.callCCClick() }

        vm.getMainActivityLiveDataState().observe(this) { mainActivityFragmentState ->
            btnNewFind.visibility = View.GONE
            when (mainActivityFragmentState) {
                is FindByNumOrTrackMainActivityState -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainLayoutCenter, FindByNumOrTrackFragment()).commitNow()
                }
                is FindByPhoneMainActivityState -> {
                    btnNewFind.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainLayoutCenter, FindByPhoneFragment()).commitNow()
                }
                is WaitingMainActivityState -> {
                    btnNewFind.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainLayoutCenter, WaitingFragment()).commitNow()
                }
                is ResultMainActivityState -> {
                    btnNewFind.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.MainLayoutCenter, SearchResultFragment()).commitNow()
                }
            }
        }
    }

    override fun findByOrderOrTrackNumClick(orderOrTrackNum: String) {
        vm.findByOrderOrTrackNumClick(orderOrTrackNum = orderOrTrackNum)
    }

    override fun findByPhoneNumClick(orderOrTrackNum: String, phoneNum: String) {
        vm.findByPhoneNumClick(orderOrTrackNum = orderOrTrackNum, _phoneNum = phoneNum)
    }

    override fun getMainActivityState(): MutableLiveData<MainActivityState> {
        return vm.getMainActivityLiveDataState()
    }

    override fun addressClick(address: String) {
        vm.addressClick(address = address)
    }
}




