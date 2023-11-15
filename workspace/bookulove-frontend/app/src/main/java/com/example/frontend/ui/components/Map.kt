package com.example.frontend.ui.components

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.frontend.data.model.UserRegistDto
import com.example.frontend.ui.vo.Routes
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
        libList: List<Map<String, String>> = listOf(),
        navController: NavHostController = rememberNavController()
    ){
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
    Log.d("location", fusedLocationClient.lastLocation.toString())
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(pos.value.latitude, pos.value.longitude), 18f)
    }
    if(!isModify){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if(location != null){
                    Log.i("location", location.toString())
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
        val uiSettings: MapUiSettings = MapUiSettings(
            compassEnabled = true,
            indoorLevelPickerEnabled = true,
            mapToolbarEnabled = true,
            myLocationButtonEnabled = true,
            rotationGesturesEnabled = true,

        )
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                pos: LatLng ->
                posInfo = pos
            },
            uiSettings = uiSettings
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
                        state = MarkerState(position = LatLng(lib.get("lat").toString().toDouble(), lib.get("lng").toString().toDouble())),
                        onInfoWindowClick = {
                            GlobalScope.launch(Dispatchers.Main){
                                navController.navigate(Routes.MYPAGE+"/${lib.get("userId").toString()}")
//                                Log.d("find user", lib.get("userId").toString())
                            }
                        }
                    ){
                        Column {
                            Text(text = lib.get("nickname").toString())
//                            PageBtn(name = lib.nickname, navController = navController, destination = Routes.MYPAGE+lib.userId)
                            FuncBtn(name = "이동", onClick = { })
                        }
                    }
                }
            }
        }
//        Button(onClick = {
//            Log.i("location", cameraPositionState.position.toString())
//            fusedLocationClient.lastLocation
//                .addOnSuccessListener { location : Location? ->
//                    if(location != null){
//                        val update = CameraUpdateFactory.newLatLngZoom(
//                            LatLng(location.latitude, location.longitude),
//                            18f)
//                        cameraPositionState.move(update)
//                    }
//                }
//        }) {
//            Text(text = "현재위치로")
//        }

    }
}