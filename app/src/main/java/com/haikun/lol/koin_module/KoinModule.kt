package com.haikun.lol.koin_module

import com.haikun.lol.repository.HomeRepository
import com.haikun.lol.request.RetrofitService
import com.haikun.lol.ui.home.HomeViewModel
import com.haikun.lol.ui.login.LoginViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object KoinModule {

    val koinModule = module {

        single {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        single {
            OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }

        single {
            Retrofit.Builder().baseUrl("http://haikun.vaiwan.com/lol/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(get())
                .build()
        }

        single { HomeRepository(get()) }

        single {
            get<Retrofit>().create(RetrofitService::class.java)
        }


        viewModel {
            LoginViewModel()
        }

        viewModel {
            HomeViewModel(get())
        }
    }
}