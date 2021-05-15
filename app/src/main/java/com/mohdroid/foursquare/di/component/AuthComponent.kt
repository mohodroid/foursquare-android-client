package com.mohdroid.foursquare.di.component

import com.mohdroid.foursquare.di.scope.ActivityScope
import com.mohdroid.foursquare.features.auth.LoginActivity
import dagger.Subcomponent

/**
 * @Subcomponent annotation informs Dagger this interface is a Dagger SubComponent
 */

@ActivityScope
@Subcomponent
interface AuthComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory{
        fun create(): AuthComponent
    }

    fun inject(loginActivity: LoginActivity)

}