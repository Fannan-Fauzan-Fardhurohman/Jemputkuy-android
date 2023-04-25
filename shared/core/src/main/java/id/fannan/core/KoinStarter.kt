package id.fannan.core

import android.content.Context
import id.fannan.auth.AuthModule
import id.fannan.network.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinStarter {
    fun onCreate(context: Context) {
        val modules = listOf(
            NetworkModule.modules(), AuthModule.modules()
        )
        startKoin {
            androidContext(context)
            modules(modules)
        }
    }
}