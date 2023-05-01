package id.fannan.ojekudriver

import android.app.Application
import id.fannan.auth.AuthModule
import id.fannan.core.KoinStarter
import id.fannan.network.NetworkModule

class MainDriver : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinStarter.onCreate(this)
    }
}