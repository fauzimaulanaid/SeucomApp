package com.fauzimaulana.seucomapp.core.di

import com.fauzimaulana.seucomapp.core.data.source.SeucomRepository
import com.fauzimaulana.seucomapp.core.data.source.remote.RemoteDataSource
import com.fauzimaulana.seucomapp.core.data.source.remote.network.ApiService
import com.fauzimaulana.seucomapp.core.domain.reposirtory.ISeucomRepository
import com.fauzimaulana.seucomapp.core.domain.usecase.SeucomInteractor
import com.fauzimaulana.seucomapp.core.domain.usecase.SeucomUseCase
import com.fauzimaulana.seucomapp.view.add.AddViewModel
import com.fauzimaulana.seucomapp.view.home.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<ISeucomRepository> { SeucomRepository(get()) }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://test-api.seucom.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val useCaseModule = module {
    factory<SeucomUseCase> { SeucomInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { AddViewModel(get()) }
}