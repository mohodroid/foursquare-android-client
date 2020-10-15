package com.mohdroid.domain.dto

import com.mohdroid.domain.CLIENT_ID
import com.mohdroid.domain.CLIENT_SECRET
import com.mohdroid.domain.VERSION

data class VenueRequest(
    val version: String = VERSION,
    val clientId: String = CLIENT_ID,
    val clientSecret: String = CLIENT_SECRET,
    val section: String,
    val ll: String,
    val limit: Int,
    val offset: Int
)