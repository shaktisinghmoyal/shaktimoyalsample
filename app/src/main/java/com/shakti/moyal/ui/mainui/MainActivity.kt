package com.shakti.moyal.ui.mainui

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.shakti.moyal.R
import com.shakti.moyal.BR
import com.shakti.moyal.databinding.ActivityMainBinding
import com.shakti.moyal.ui.baseui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityModel>(), MainActivityNavigator {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mMainActivityModel: MainActivityModel? = null

    private var mActivityMainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainActivityModel?.setNavigator(this)
        mActivityMainBinding = getViewDataBinding()
    }

    // Functions to be implemented by every Activity
    override fun getViewModel(): MainActivityModel {
        mMainActivityModel = ViewModelProviders.of(this, mViewModelFactory).get(MainActivityModel::class.java)
        return mMainActivityModel as MainActivityModel
    }

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_main

    // Navigator Functions
    // navigator not being used.
    override fun errorOccured(throwable: Throwable) {

    }

}