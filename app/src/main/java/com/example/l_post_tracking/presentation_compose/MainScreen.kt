package com.example.l_post_tracking.presentation_compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.l_post_tracking.R
import com.example.l_post_tracking.model.FindByNumOrTrackMainScreenState
import com.example.l_post_tracking.model.MainScreenState
import com.example.l_post_tracking.model.WaitingMainScreenState
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
        MainScreenHeader()
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { vm.newSearchClick() }, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Новый поиск")
        }
        Spacer(modifier = Modifier.height(30.dp))
        ScreenStateElement(mainScreenState) { vm.findByOrderOrTrackNumClick(it) }
    }
}

@Composable
fun MainScreenHeader() {

    Log.e("AAA_AAA", "MainScreenHeader")

    Row {
        Image(
            painter = painterResource(id = R.drawable.main_page_logo),
            contentDescription = null,
            modifier = Modifier.width(150.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "8 800 700–1006",
            color = Color.White,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {})
    }
}

@Composable
fun ScreenStateElement(mainScreenState: State<MainScreenState?>, onClickFindByTrack: (String) -> Unit) {

    Log.e("AAA_AAA", "ScreenStateElement $mainScreenState")

    val txtEditFindByTrack = remember(mainScreenState.value) { mutableStateOf("") }
    when (val state = mainScreenState.value) {
        is FindByNumOrTrackMainScreenState -> {
            FindByNumOrTrackElement(txtError = state.errorMsg,
                txtEdit = txtEditFindByTrack,
                onEditChange = { txtEditFindByTrack.value = it },
                onFindClick = { onClickFindByTrack(txtEditFindByTrack.value) })
        }
        is WaitingMainScreenState -> WaitingElement()
        else -> Text(text = "empty")
    }
}