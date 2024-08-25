package com.example.androiddrugbugtests

import android.app.Application
import com.example.androiddrugbugtests.repository.InteractionsRespository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@HiltAndroidApp
class AndroidDrugBugTestsApplication : Application()

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideInteractionsRepository() = InteractionsRespository()
}