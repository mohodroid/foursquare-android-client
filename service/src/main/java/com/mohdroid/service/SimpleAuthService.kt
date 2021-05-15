package com.mohdroid.service

import android.util.Log
import javax.inject.Inject


class SimpleAuthService @Inject constructor() {

    private lateinit var emailAddress: String

    fun register(emailAddress: String) {
        this.emailAddress = emailAddress
    }
}