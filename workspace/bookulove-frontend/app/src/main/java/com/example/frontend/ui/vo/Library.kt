package com.example.frontend.ui.vo

import com.google.android.gms.maps.model.LatLng

data class Library(
    val userId: String,
    var libPos: LatLng,
    var title: String,
    var detail: String,
)