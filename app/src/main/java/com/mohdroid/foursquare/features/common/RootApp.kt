package com.mohdroid.foursquare.features.common

import android.app.Application
import com.mohdroid.foursquare.di.component.AppGraph
import com.mohdroid.foursquare.di.component.DaggerAppGraph
import com.mohdroid.foursquare.di.module.AppModule
import com.mohdroid.repository.di.module.RepositoryModule

class RootApp : Application() {

    companion object {
        private val TAG = RootApp::class.java.name
//        private lateinit var graph: AppGraph
//        fun getComponent(): AppGraph =
//            graph
    }
//
//    private fun appComponent(): AppGraph {
//        return DaggerAppGraph.
//    }


    lateinit var appGraph: AppGraph
    override fun onCreate() {
        super.onCreate()
        appGraph =  DaggerAppGraph.builder()
            .appModule(AppModule(this))
            .repositoryModule(RepositoryModule(this))
            .build()
    }

}