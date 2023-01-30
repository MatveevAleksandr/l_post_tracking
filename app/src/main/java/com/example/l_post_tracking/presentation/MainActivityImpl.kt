package com.example.l_post_tracking.presentation

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.l_post_tracking.R
import com.example.l_post_tracking.app.App
import com.example.l_post_tracking.di.DaggerAppComponent
import com.example.l_post_tracking.model.*
import com.example.l_post_tracking.viewmodel.MainViewModel
import com.example.l_post_tracking.viewmodel.MainViewModelFactory
import javax.inject.Inject

/**
 * темная тема
 * dagger - разнести по модулям проекта/ компонент надо создавать в том модуле, где находится объект, куда компонент будет инджектить
 * не работают кейсы с контекстом
 * compose
 */

class MainActivityImpl : AppCompatActivity(), IMainActivity.IFindByNumOrTrackFragment,
    IMainActivity.IFindByPhoneFragment, IMainActivity.ISearchResultFragment {

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as App).appComponent.injectMainActivity(this)

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

    override fun getMainActivityLiveDataState(): MutableLiveData<MainActivityState> {
        return vm.getMainActivityLiveDataState()
    }

    override fun addressClick(address: String) {
        vm.addressClick(address = address)
    }
}




