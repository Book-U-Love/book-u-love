package com.example.frontend.data.repository

import com.example.frontend.data.local.ApplicationPrefs

class PrefsRepository {
    fun getValue(key:String):String{
        return ApplicationPrefs.prefs.getString(key,"")
    }
    fun setValue(key:String, value:String){
        ApplicationPrefs.prefs.setString(key, value)
    }
}