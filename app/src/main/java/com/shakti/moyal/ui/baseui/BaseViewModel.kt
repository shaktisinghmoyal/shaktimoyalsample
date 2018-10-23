package com.shakti.moyal.ui.baseui

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.shakti.moyal.data.DataProvider
import com.shakti.moyal.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference


abstract class BaseViewModel<N>(val theDataProvider: DataProvider,
                                val theSchedulerProvider: SchedulerProvider, val theCompositeDisposable: CompositeDisposable) : ViewModel() {

    val isLoading = ObservableBoolean(false)

    var mNavigator: WeakReference<N> ? = null

    override fun onCleared() {
        theCompositeDisposable.dispose()
        super.onCleared()
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    fun setNavigator(nav : N)
    {
        mNavigator = WeakReference(nav)
    }

    fun getCompositeDisposable() = theCompositeDisposable
    fun getSchedulerProvider() = theSchedulerProvider
    fun getDataManager() = theDataProvider
    fun getNavigator() = mNavigator?.get()
}
