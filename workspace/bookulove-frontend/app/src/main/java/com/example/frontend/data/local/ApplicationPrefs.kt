package com.example.frontend.data.local

import android.app.Application

class ApplicationPrefs:Application(){
    companion object{
        lateinit var prefs : PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceUtil(applicationContext)
    }
}