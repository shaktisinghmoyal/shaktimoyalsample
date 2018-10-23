package com.shakti.moyal.ui.mainui

import android.arch.lifecycle.ViewModelProvider
import com.shakti.moyal.data.DataProvider
import com.shakti.moyal.di.factories.ViewModelProviderFactory
import com.shakti.moyal.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MainActivityModule
{
    @Provides
    fun provideMainViewModel(dataProvider: DataProvider, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable) = MainActivityModel(dataProvider, schedulerProvider, compositeDisposable)

    @Provides
    fun provideMainViewModelFactory(mainViewModel: MainActivityModel) : ViewModelProvider.Factory = ViewModelProviderFactory<MainActivityModel>(mainViewModel)

}