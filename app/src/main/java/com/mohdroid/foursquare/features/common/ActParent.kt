package com.mohdroid.foursquare.features.common

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mohdroid.foursquare.features.common.viewmodel.AbsViewModel

abstract class ActParent<T : AbsViewModel> : ActBase() {


    protected lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, getFactory()).get(getViewModelClass())
        lifecycle.addObserver(this.viewModel)
        this.viewModel.getShowProgressEvent().observe(this,
            EventObserver {
                showProgress()
            })
        this.viewModel.getHideProgressEvent().observe(this,
            EventObserver {
                hideProgress()
            })
        this.viewModel.getShowNoNetworkErrorEvent().observe(this,
            EventObserver {
                Toast.makeText(this, getString(it), Toast.LENGTH_LONG).show()
            })
        this.viewModel.getShowErrorEvent().observe(this,
            EventObserver {
                Toast.makeText(this, getString(it), Toast.LENGTH_LONG).show()
            })
        this.viewModel.getShowWarningEvent().observe(this,
            EventObserver {
                showToast(it)
            })

    }

    protected open fun showProgress() {}

    protected open fun hideProgress() {}

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    protected abstract fun getFactory(): ViewModelProvider.Factory

    protected abstract fun getViewModelClass(): Class<T>

    /**
     * Passing in an instance of the Activity that requests injection.
     * Make dagger instantiate @Inject field.
     * Calling before super.onCreate() to avoid issue with fragments
     */
    protected abstract fun inject()


}