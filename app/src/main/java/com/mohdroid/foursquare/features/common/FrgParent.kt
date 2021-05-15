package com.mohdroid.foursquare.features.common

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mohdroid.foursquare.features.common.viewmodel.AbsViewModel

abstract class FrgParent<T : AbsViewModel> : Fragment() {


    protected lateinit var viewModel: T

    protected lateinit var actParent: ActBase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actParent = activity as ActBase
        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, getFactory()).get(getFactoryClass())
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
                Toast.makeText(actParent, it, Toast.LENGTH_LONG).show()
            })
        this.viewModel.getShowErrorEvent().observe(this,
            EventObserver {
                Toast.makeText(actParent, it, Toast.LENGTH_LONG).show()
            })
        this.viewModel.getShowWarningEvent().observe(this,
            EventObserver {
                showToast(it)
            })
    }

    protected abstract fun getFactory(): ViewModelProvider.Factory

    protected abstract fun getFactoryClass(): Class<T>

     abstract  fun showProgress()

    abstract  fun hideProgress()

    /**
     * Passing in an instance of the Fragment that requests injection.
     * Make dagger instantiate @Inject field
     */
    protected abstract fun inject()

    fun showToast(text: String) {
        Toast.makeText(actParent, text, Toast.LENGTH_LONG).show()
    }
}