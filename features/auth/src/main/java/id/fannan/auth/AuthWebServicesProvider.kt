package id.fannan.auth

object AuthWebServicesProvider {
    fun providerWebServices(): AuthWebservices {
        return AuthWebservices.build()
    }
}