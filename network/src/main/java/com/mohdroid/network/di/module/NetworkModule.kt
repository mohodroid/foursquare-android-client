package com.mohdroid.network.di.module

import android.content.Context
import com.google.gson.GsonBuilder
import com.mohdroid.domain.network.VenueNetwork
import com.mohdroid.network.di.qualifier.*
import com.mohdroid.network.interceptor.BodyLogger
import com.mohdroid.network.interceptor.RequestHeaderInterceptor
import com.mohdroid.network.networks.VenueNetworkImpl
import com.mohdroid.network.remoteservices.RemoteServiceProvider
import com.mohdroid.network.remoteservices.RemoteServiceProviderImpl
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@Module
class NetworkModule {


    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = "https://api.foursquare.com/v2/"

    @Provides
    @LogginInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @BodyLogginInterceptor
    fun provideBodyLoggingInterceptor(): Interceptor = BodyLogger()

    @Provides
    @GsonConverter
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Provides
    fun provideCache(ctx: Context): Cache {
        val httpCacheDirectory = File(ctx.cacheDir, "http-cache")
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(httpCacheDirectory, cacheSize.toLong())
    }

    @Provides
    @RequestHeader
    fun provideRequestHeaderInterceptor(
    ): Interceptor {
        return RequestHeaderInterceptor()
    }

    @Provides
    fun provideOkHttp(
        @LogginInterceptor loggingInterceptor: Interceptor,
        @BodyLogginInterceptor bodyLoggingInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(bodyLoggingInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        @GsonConverter gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideRemoteServiceProvider(retrofit: Retrofit): RemoteServiceProvider =
        RemoteServiceProviderImpl(retrofit)

    @Provides
    fun provideAllVenuesList(allVenuesListImpl: VenueNetworkImpl): VenueNetwork =
        allVenuesListImpl

}