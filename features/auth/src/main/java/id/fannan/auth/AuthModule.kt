package id.fannan.auth

import org.koin.dsl.module

object AuthModule {

    fun modules() = module {
        single {
            AuthWebServicesProvider.providerWebServices()
        }
    }
}