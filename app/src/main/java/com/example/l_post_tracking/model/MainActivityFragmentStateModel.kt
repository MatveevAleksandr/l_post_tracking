package com.example.l_post_tracking.model

enum class MainActivityFragment{
    FIND_BY_NUM_OR_TRACK, FIND_BY_PHONE, WAITING, RESULT
}

data class MainActivityFragmentStateModel (
    val mainActivityFragment: MainActivityFragment,
    val errorMsg: String?
    )