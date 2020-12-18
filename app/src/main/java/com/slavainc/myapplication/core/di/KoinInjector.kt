package com.slavainc.myapplication.core.di

import android.content.Context
import com.slavainc.apollo.ApolloInitializer
import com.slavainc.apollo.ApolloManager
import com.slavainc.apollo.ApolloManagerImpl
import com.slavainc.mapper.*
import com.slavainc.myapplication.feature.detail.vm.DetailViewModel
import com.slavainc.myapplication.feature.login.vm.LoginViewModel
import com.slavainc.myapplication.feature.main.vm.MainViewModel
import com.slavainc.repository.MainRepository
import com.slavainc.repository.MainRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object KoinInjector {

    fun setup(context: Context) {
        startKoin {
            androidContext(context)
            modules(listOf(viewModelsModule, managerModule, repositoryModule, mapperModule))
        }
    }

    private val repositoryModule = module {
        factory<MainRepository> { MainRepositoryImpl(get(),androidContext()) }
    }

    private val managerModule = module {
        single<ApolloManager> { ApolloManagerImpl(get()) }
        single { ApolloInitializer(androidContext()) }
    }

    private val viewModelsModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { LoginViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }

    private val mapperModule = module {
        factory { LaunchMapper(get(), get()) }
        factory { RocketMapper() }
        factory { MissionMapper() }
        factory { LaunchListMapper(get()) }
        factory { MissionListMapper() }
        factory { LaunchListQueryMapper(get()) }
    }
}