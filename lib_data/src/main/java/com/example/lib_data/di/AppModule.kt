package com.example.lib_data.di

import com.example.lib_data.data.ApiService
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.repository.Repository
import com.example.lib_data.util.ConstantsURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object AppModule {
//
    @Provides
    @Singleton
    fun providesOkHttpClient():OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
               val requestBuilder = chain.request().newBuilder()
                requestBuilder.header("Content-Type", "application/json")
               return chain.proceed(requestBuilder.build())
            }
        })
        .build()
//
    @Singleton
    @Provides
    fun providesApisService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder().baseUrl(ConstantsURL.BASE_URL).client(okHttpClient).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)!!
    }
    //
    @Singleton
    @Provides
    fun provideRepositoryImpl(apiService: ApiService): Repository = RepositoryImpl(apiService)
//
//    @Singleton
//    @Provides
//    fun providesRepo(apiService: ApiService): Repository =
//        RepositoryImpl(apiService)
//



}
