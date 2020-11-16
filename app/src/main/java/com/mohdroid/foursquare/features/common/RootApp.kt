package com.mohdroid.foursquare.features.common

import android.app.Application
import com.mohdroid.foursquare.di.component.AppComponent
import com.mohdroid.foursquare.di.component.DaggerAppComponent
import com.mohdroid.foursquare.di.module.AppModule
import com.mohdroid.repository.di.module.RepositoryModule

class RootApp : Application() {

    companion object {
        private val TAG = RootApp::class.java.name
        private lateinit var component: AppComponent
        fun getComponent(): AppComponent =
            component
    }

    private fun appComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .repositoryModule(RepositoryModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component = appComponent()
    }

}