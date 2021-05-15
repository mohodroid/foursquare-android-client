package com.mohdroid.foursquare.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import javax.inject.Inject
import javax.inject.Singleton

interface LoadImageHelper {

    fun loadImage(url: String, view: ImageView)

}

@Singleton
class LoadImageHelperImpl @Inject constructor(ctx: Context) :
    LoadImageHelper {
    private val glide: RequestManager = Glide.with(ctx)

    override fun loadImage(url: String, view: ImageView) {
        handleLoadImage {
            glide
                .load(url)
                .into(view)
        }
    }

    private fun handleLoadImage(loadImage: () -> Unit) {
        try {
            loadImage()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}