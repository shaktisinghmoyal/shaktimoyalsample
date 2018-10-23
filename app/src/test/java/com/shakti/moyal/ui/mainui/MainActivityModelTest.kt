package com.shakti.moyal.ui.mainui

import android.databinding.Observable
import com.shakti.moyal.data.DataProvider
import com.shakti.moyal.utils.rx.TestSchedulerProvider
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import android.databinding.ObservableField
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.ArgumentCaptor






@RunWith(MockitoJUnitRunner::class)
class MainActivityModelTest {


    @Mock
    lateinit var mMockDataProvider: DataProvider

    @Mock
    lateinit var mMockPropertyChangedCallbackFor10thLetter: Observable.OnPropertyChangedCallback

    @Mock
    lateinit var mMockPropertyChangedCallbackForEvery10thLetter: Observable.OnPropertyChangedCallback

    @Mock
    lateinit var mMockPropertyChangedCallbackForWordFrequencyRequests: Observable.OnPropertyChangedCallback

    private var mMainActivityModel: MainActivityModel? = null
    private val mTestScheduler: TestScheduler = TestScheduler()
    private val compositeDisposable = CompositeDisposable()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val testSchedulerProvider = TestSchedulerProvider(mTestScheduler)
        mMainActivityModel = MainActivityModel(mMockDataProvider, testSchedulerProvider,compositeDisposable)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        compositeDisposable.dispose()
    }

    @Test
    fun testAllThreeRequestsEnd() {
        val testString = "Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique.Hello, my name is Shakti Singh. Currently Working at Rubique."
        val test10thLetterAnswer = "e"
        val testEvery10thLetterAnswer = "eii,iSrkbo"
        val testFrequency10thLetterAnswer = "Rubique.Hello,   =   1\n" +
                "Working   =   2\n" +
                "Currently   =   2\n" +
                "at   =   2\n" +
                "Rubique.Hello   =   1\n" +
                "name   =   1\n" +
                "Singh.   =   1\n" +
                "is   =   1\n" +
                "ngh.   =   1\n" +
                "my   =   1\n" +
                "Shakti   =   1\n"

        // mocking api to return test string
        whenever(mMockDataProvider.getBlogPostData(com.nhaarman.mockito_kotlin.anyOrNull(),com.nhaarman.mockito_kotlin.anyOrNull(),com.nhaarman.mockito_kotlin.anyOrNull(),com.nhaarman.mockito_kotlin.anyOrNull())).thenReturn(Single.just(testString))

        mMainActivityModel?.t10thCharacterRequestAnswer?.addOnPropertyChangedCallback(mMockPropertyChangedCallbackFor10thLetter)
        mMainActivityModel?.tevery10thCharacterRequestAnswer?.addOnPropertyChangedCallback(mMockPropertyChangedCallbackForEvery10thLetter)
        mMainActivityModel?.tWordCounterRequestAnswer?.addOnPropertyChangedCallback(mMockPropertyChangedCallbackForWordFrequencyRequests)

        val argumentCaptureFor10thLetter = ArgumentCaptor.forClass(Observable::class.java)
        val argumentCaptureForEvery10thLetter = ArgumentCaptor.forClass(Observable::class.java)
        val argumentCaptureForWordFrequencyRequests = ArgumentCaptor.forClass(Observable::class.java)

        // call webpage
        mMainActivityModel?.getWebPage()
        mTestScheduler.triggerActions()


        verify(mMockPropertyChangedCallbackFor10thLetter, times(2)).onPropertyChanged(argumentCaptureFor10thLetter.capture(), ArgumentMatchers.anyInt())

        val valuesFor10thLetter = argumentCaptureFor10thLetter.allValues
        val mVal0valuesFor10thLetter = valuesFor10thLetter[0] as ObservableField<String>
        assert(mVal0valuesFor10thLetter.get().equals(test10thLetterAnswer))


        verify(mMockPropertyChangedCallbackForEvery10thLetter, times(2)).onPropertyChanged(argumentCaptureForEvery10thLetter.capture(), ArgumentMatchers.anyInt())

        val valuesForEvery10thLetter = argumentCaptureForEvery10thLetter.allValues
        val mVal0valuesForEvery10thLetter = valuesForEvery10thLetter[0] as ObservableField<String>
        assert(mVal0valuesForEvery10thLetter.get().equals(testEvery10thLetterAnswer))

        verify(mMockPropertyChangedCallbackForWordFrequencyRequests, times(2)).onPropertyChanged(argumentCaptureForWordFrequencyRequests.capture(), ArgumentMatchers.anyInt())

        val valuesForWordFrequencyRequests = argumentCaptureForWordFrequencyRequests.allValues
        val mVal0ForWordFrequencyRequests = valuesForWordFrequencyRequests[0] as ObservableField<String>

        assert(mVal0ForWordFrequencyRequests.get().equals(testFrequency10thLetterAnswer))
    }
}