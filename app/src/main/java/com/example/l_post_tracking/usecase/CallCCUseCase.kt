package com.example.l_post_tracking.usecase

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat


private const val CC_PHONE = "88007001006"

class CallCCUseCase {
    fun exec(app: Application){
        val intPhoneCall = Intent(Intent.ACTION_DIAL)
        intPhoneCall.data = Uri.parse("tel:$CC_PHONE")
        Log.e("AAA_AAA", app.applicationContext.toString())
        ContextCompat.startActivity(app.applicationContext, intPhoneCall, null)
    }
}