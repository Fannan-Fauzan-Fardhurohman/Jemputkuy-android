package id.fannan.cloningojekonlineandroid

import org.koin.androidx.viewmodel.dsl.viewModel

object HomeModule {
    fun module() = org.koin.dsl.module {
        single<HomeRepository> {
            HomeRepositoryImpl(get())
        }
        viewModel {
            HomeViewModel(get())
        }
    }
}