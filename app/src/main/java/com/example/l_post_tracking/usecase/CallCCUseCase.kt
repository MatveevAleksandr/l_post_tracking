package com.example.l_post_tracking.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat


private const val CC_PHONE = "88007001006"

class CallCCUseCase(private val context: Context) {
    fun exec(){
        val intPhoneCall = Intent(Intent.ACTION_DIAL)
        intPhoneCall.data = Uri.parse("tel:$CC_PHONE")
        ContextCompat.startActivity(context, intPhoneCall, null)
    }
}