package id.fannan.cloningojekonlineandroid

import android.app.Application
import id.fannan.core.KoinStarter

class MainCustomer : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinStarter.onCreate(
            this, listOf(
                HomeModule.module()
            )
        )
    }
}