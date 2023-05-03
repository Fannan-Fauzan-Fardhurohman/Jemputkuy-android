package id.fannan.cloningojekonlineandroid.listener

import android.location.Location
import id.fannan.listener.ActivityListener

interface MainActivityListener :ActivityListener{
    fun onLocationResult(data: Location)
}