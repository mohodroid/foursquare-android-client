package com.mohdroid.foursquare.features.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.mohdroid.foursquare.R
import com.mohdroid.foursquare.di.component.AuthComponent
import com.mohdroid.foursquare.features.MainActivity
import com.mohdroid.foursquare.features.common.ActParent
import com.mohdroid.foursquare.features.common.RootApp
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : ActParent<LoginViewModel>() {

    //Fields that need to be injected by the auth graph
    @Inject
    lateinit var factory: LoginViewModel.Factory

    //Reference to the auth graph
    lateinit var authComponent: AuthComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        //Creation of the auth graph using the application graph
        authComponent = (applicationContext as RootApp).appGraph.authComponent().create()
        //viewModel is available
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnConfirm.setOnClickListener {
            viewModel.emailAddress(etEmailAddress.text.trim().toString())
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun getFactory(): ViewModelProvider.Factory = factory

    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun inject() {
        authComponent.inject(this)
    }
}