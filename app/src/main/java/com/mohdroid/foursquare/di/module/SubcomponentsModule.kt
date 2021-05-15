package com.mohdroid.foursquare.di.module

import com.mohdroid.foursquare.di.component.AuthComponent
import dagger.Module


/**
 * @param subcomponents Subcomponents are children of the Component this module is included in
 */
@Module(subcomponents = [AuthComponent::class])
class SubcomponentsModule {}