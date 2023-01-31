package com.example.l_post_tracking.usecase

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.core.content.ContextCompat


private const val CC_PHONE = "88007001006"

class CallCCUseCase(private val context: Context) {
    fun exec(){
        val intPhoneCall = Intent(Intent.ACTION_DIAL)
        intPhoneCall.data = Uri.parse("tel:$CC_PHONE")
        intPhoneCall.flags = FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(context, intPhoneCall, null)
    }
}