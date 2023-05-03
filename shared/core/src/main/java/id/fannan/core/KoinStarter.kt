package id.fannan.core

import android.content.Context
import id.fannan.auth.AuthModule
import id.fannan.network.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object KoinStarter {
    fun onCreate(context: Context, featureModule: List<Module> = emptyList()) {
        val modules = listOf(
            CoreModules.modules(),
            NetworkModule.modules(),
            UtilsModule.modules()
        ) + featureModule
        startKoin {
            androidContext(context)
            modules(modules)
        }
    }
}