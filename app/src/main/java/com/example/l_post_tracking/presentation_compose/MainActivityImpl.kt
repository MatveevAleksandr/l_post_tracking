package com.example.l_post_tracking.presentation_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.l_post_tracking.app.App
import com.example.l_post_tracking.model.MainScreenState
import com.example.l_post_tracking.viewmodel.MainViewModel
import com.example.l_post_tracking.viewmodel.MainViewModelFactory
import javax.inject.Inject

/**
 * темная тема
 * кнопка новый поиск
 * проверить поиск сномером телефона
 * велком активити в компоуз
 * удалить фрагменты
 * удалить IMainActivity
 * MainScreen di
 */

class MainActivityImpl : ComponentActivity(), IMainActivity.IFindByNumOrTrackFragment,
    IMainActivity.IFindByPhoneFragment, IMainActivity.ISearchResultFragment {

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as App).appComponent.injectMainActivity(this)
        vm = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]

        setContent {
            MainScreen(vm)
        }


//        setContentView(R.layout.activity_main)
//
//        (applicationContext as App).appComponent.injectMainActivity(this)
//        vm = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
//
//        val btnNewFind = findViewById<Button>(R.id.btnNewFind)
//        btnNewFind.setOnClickListener { vm.newSearchClick() }
//
//        findViewById<TextView>(R.id.tvHeadCCPhone).setOnClickListener { vm.callCCClick() }
//
//        vm.getMainActivityLiveDataState().observe(this) { mainActivityFragmentState ->
//            btnNewFind.visibility = View.GONE
//            when (mainActivityFragmentState) {
//                is FindByNumOrTrackMainActivityState -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.MainLayoutCenter, FindByNumOrTrackFragment()).commitNow()
//                }
//                is FindByPhoneMainActivityState -> {
//                    btnNewFind.visibility = View.VISIBLE
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.MainLayoutCenter, FindByPhoneFragment()).commitNow()
//                }
//                is WaitingMainActivityState -> {
//                    btnNewFind.visibility = View.VISIBLE
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.MainLayoutCenter, WaitingFragment()).commitNow()
//                }
//                is ResultMainActivityState -> {
//                    btnNewFind.visibility = View.VISIBLE
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.MainLayoutCenter, SearchResultFragment()).commitNow()
//                }
//            }
//        }
    }

    override fun findByOrderOrTrackNumClick(orderOrTrackNum: String) {
        vm.findByOrderOrTrackNumClick(orderOrTrackNum = orderOrTrackNum)
    }

    override fun findByPhoneNumClick(orderOrTrackNum: String, phoneNum: String) {
        vm.findByPhoneNumClick(orderOrTrackNum = orderOrTrackNum, _phoneNum = phoneNum)
    }

    override fun getMainActivityLiveDataState(): LiveData<MainScreenState> {
        return vm.getMainScreenLiveDataState()
    }

    override fun addressClick(address: String) {
        vm.addressClick(address = address)
    }
}




