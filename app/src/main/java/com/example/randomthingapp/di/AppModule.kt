package com.example.randomthingapp.di

import com.example.randomthingapp.data.remote.YesNoApi
import com.example.randomthingapp.data.repository.YesNoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://yesno.wtf/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideYesNoApi(retrofit: Retrofit): YesNoApi {
        return retrofit.create(YesNoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideYesNoRepository(api: YesNoApi): YesNoRepository {
        return YesNoRepository(api)
    }
}


