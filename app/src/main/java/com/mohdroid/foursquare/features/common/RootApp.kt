package com.mohdroid.foursquare.features.common

import android.app.Application
import com.mohdroid.foursquare.di.component.AppGraph
import com.mohdroid.foursquare.di.component.DaggerAppGraph
import com.mohdroid.foursquare.di.module.AppModule
import com.mohdroid.persistent.di.module.PersistentModule

class RootApp : Application() {

    companion object {
        private val TAG = RootApp::class.java.name
    }

    lateinit var appGraph: AppGraph
    override fun onCreate() {
        super.onCreate()
        appGraph = DaggerAppGraph.builder()
            .appModule(AppModule(this))
            .persistentModule(PersistentModule(this))
            .build()
    }

}