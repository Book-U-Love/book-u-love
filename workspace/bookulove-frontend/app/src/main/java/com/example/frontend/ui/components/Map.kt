package com.example.frontend.ui.components

import android.annotation.SuppressLint
import android.graphics.pdf.PdfDocument.Page
import android.location.Location
import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.frontend.ui.vo.Library
import com.example.frontend.ui.vo.Routes
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.UiSettings
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
@Composable
fun MapInfo(
        width: Int = 400,
        height: Int = 400,
        isModify: Boolean = false,
        pos: MutableState<LatLng>,
        title: String = "",
        detail: String = "",
        libList: List<Library> = listOf(),
        navController: NavHostController = rememberNavController()
    ){
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(pos.value.latitude, pos.value.longitude), 18f)
    }
    if(!isModify){
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
            .height(height.dp)
            .width(width.dp)
    ){
        var posInfo = LatLng(0.0, 0.0)
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                pos: LatLng ->
                posInfo = pos
            }
        ) {
            if(libList.isEmpty()){
                Marker(
                    state = MarkerState(position = cameraPositionState.position.target),
                    title = title,
                    snippet = detail
                )
                pos.value = LatLng(cameraPositionState.position.target.latitude, cameraPositionState.position.target.longitude)
            } else{
                for(lib in libList){
                    MarkerInfoWindowContent (
                        state = MarkerState(position = lib.libPos),
                        onInfoWindowClick = {
                            GlobalScope.launch(Dispatchers.Main){
                                navController.navigate(Routes.MYPAGE+"/${lib.userId}")
                            }
                        }
                    ){
                        Column {
                            Text(text = lib.detail)
                            FuncBtn(name = lib.title){}
                        }
                    }
                }
            }
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