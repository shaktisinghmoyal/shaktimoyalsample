package com.shakti.moyal.di.modules

import android.app.Application
import android.content.Context
import com.shakti.moyal.data.DataProvider
import com.shakti.moyal.data.DataProviderImpl
import com.shakti.moyal.data.repository.ApiRepository
import com.shakti.moyal.data.repository.ApiRepositoryImpl
import com.shakti.moyal.util.rx.SchedulerProvider
import com.shakti.moyal.util.rx.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDataManager(appDataManager: DataProviderImpl): DataProvider = appDataManager

    @Singleton
    @Provides
    fun provideScheduleProvider(): SchedulerProvider = SchedulerProviderImpl()

    @Singleton
    @Provides
    fun provideApiDataHelper(retrofit: Retrofit): ApiRepository = ApiRepositoryImpl(retrofit)

    @Singleton
    @Provides
    fun provideContext(app: Application): Context = app.baseContext

    @Singleton
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()
}