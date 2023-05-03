package id.fannan.core

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object UtilsModule {
    fun modules() = module{
        single {
            LocationManager(androidContext())
        }
    }
}