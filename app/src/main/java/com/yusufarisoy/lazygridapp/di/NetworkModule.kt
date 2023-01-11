package com.yusufarisoy.lazygridapp.di

import com.google.gson.Gson
import com.yusufarisoy.lazygridapp.data.repository.datasource.CharactersDataSource
import com.yusufarisoy.lazygridapp.data.repository.datasource.CharactersDataSourceImpl
import com.yusufarisoy.lazygridapp.network.ApiService
import com.yusufarisoy.lazygridapp.network.EndPoints.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideCharactersDataSource(service: ApiService): CharactersDataSource =
        CharactersDataSourceImpl(service)

    @Provides
    fun provideRickAndMortyService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}
