package com.example.l_post_tracking.presentation_compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.l_post_tracking.model.WaitingMainScreenState
import com.example.l_post_tracking.viewmodel.MainViewModel

@Composable
fun MainScreen(
    vm: MainViewModel
) {

    Log.e("AAA_AAA", "MainScreen")

    val mainScreenState = vm.getMainScreenLiveDataState().observeAsState()
    val edTxt = remember { mutableStateOf("") }

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
        Log.e("AAA_AAA", "MainScreen1")
        MainScreenHeader()
        Log.e("AAA_AAA", "MainScreen2")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { vm.newSearchClick() }, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Новый поиск")
        }
        Spacer(modifier = Modifier.height(30.dp))

        when (mainScreenState.value) {
            is FindByNumOrTrackMainScreenState -> FindByNumOrTrackElement(txtError = null,
                txtEdit = edTxt,
                onEditChange = { edTxt.value = it },
                onFindClick = {
                    vm.findByOrderOrTrackNumClick(
                        "22"
                    )
                })


            is WaitingMainScreenState -> WaitingElement()
            else -> Text(text = "empty")
        }

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
