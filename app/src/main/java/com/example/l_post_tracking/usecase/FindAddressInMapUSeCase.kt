package com.example.l_post_tracking.usecase

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

class FindAddressInMapUseCase(private val context: Context) {
    fun exec(address: String){
        val geoUriString = "geo:0,0?q=$address&z=8"
        val geoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUriString))
        ContextCompat.startActivity(context, geoIntent, null)
    }
}