package com.example.frontend.ui.components

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("MissingPermission")
@Composable
fun MapInfo(){
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(37.566535, 126.97796919), 18f)
    }
    SideEffect {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if(location != null){
                    val update = CameraUpdateFactory.newLatLngZoom(
                        LatLng(location.latitude, location.longitude),
                        18f)
                    cameraPositionState.move(update)
                }
            }
    }
    Box(
        modifier = Modifier
            .height(600.dp)
            .width(450.dp)
    ){

        var posInfo = LatLng(0.0, 0.0)
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                pos: LatLng ->
                posInfo = pos
            }
        ) {
            Marker(
                state = MarkerState(position = cameraPositionState.position.target),
                title = "Click",
                snippet = "Click"
            )
        }
        Button(onClick = {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    if(location != null){
                        val update = CameraUpdateFactory.newLatLngZoom(
                            LatLng(location.latitude, location.longitude),
                            18f)
                        cameraPositionState.move(update)
                    }
                }
        }) {
            Text(text = "현재위치로")
        }
    }
}
