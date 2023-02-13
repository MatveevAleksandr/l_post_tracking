package com.example.l_post_tracking.presentation_compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.l_post_tracking.R
import com.example.l_post_tracking.model.*
import com.example.l_post_tracking.viewmodel.MainViewModel

@Composable
fun MainScreen(
    vm: MainViewModel
) {

    Log.e("AAA_AAA", "MainScreen")

    val mainScreenState = vm.getMainScreenLiveDataState().observeAsState()
    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(red = 91, green = 37, blue = 153),
                        Color(red = 0, green = 222, blue = 233)
                    )
                )
            )
            .fillMaxSize()
            .padding(15.dp)
    ) {
        MainScreenHeader(onCCPhoneClick = { vm.callCCClick() })
        Spacer(modifier = Modifier.height(20.dp))
        MainScreenNewFindElement(
            state = mainScreenState,
            onNewSearchClick = { vm.newSearchClick() },
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(30.dp))
        MainScreenStateElement(_state = mainScreenState, onFindByNumOrTrackClick = { numOrTrack ->
            vm.findByOrderOrTrackNumClick(orderOrTrackNum = numOrTrack)
        }, onFindByPhoneClick = { numOrTrack, phoneNum ->
            vm.findByPhoneNumClick(orderOrTrackNum = numOrTrack, _phoneNum = phoneNum)
        })
    }
}

@Composable
fun MainScreenNewFindElement(
    state: State<MainScreenState?>, onNewSearchClick: () -> Unit, modifier: Modifier
) {

    Log.e("AAA_AAA", "MainScreenNewFindElement")

    if (state.value !is FindByNumOrTrackMainScreenState) {
        Button(onClick = onNewSearchClick, modifier = modifier) {
            Text(text = "Новый поиск")
        }
    }
}

@Composable
fun MainScreenHeader(onCCPhoneClick: () -> Unit) {

    Log.e("AAA_AAA", "MainScreenHeader")

    Row {
        Image(
            painter = painterResource(id = R.drawable.main_page_logo),
            contentDescription = null,
            modifier = Modifier.width(150.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "8 800 700–1006",
            color = Color.White,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable(onClick = onCCPhoneClick)
        )
    }
}

@Composable
fun MainScreenStateElement(
    _state: State<MainScreenState?>,
    onFindByNumOrTrackClick: (numOrTrack: String) -> Unit,
    onFindByPhoneClick: (numOrTrack: String, phoneNum: String) -> Unit
) {
    when (val state: MainScreenState = _state.value!!) {
        is FindByNumOrTrackMainScreenState -> {
            FindByNumOrTrackElement(txtError = state.errorMsg, onFindClick = {
                onFindByNumOrTrackClick(it)
            })
        }
        is FindByPhoneMainScreenState -> {
            FindByPhoneElement(
                orderNumber = state.orderNum,
                txtError = state.errorMsg,
                onFindClick = {
                    onFindByPhoneClick(state.orderNum, it)
                })
        }
        is WaitingMainScreenState -> WaitingElement()
        is ResultMainScreenState -> {}
    }
}