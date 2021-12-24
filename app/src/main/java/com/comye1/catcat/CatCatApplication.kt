package com.comye1.catcat

import android.app.Application
import android.util.Log
import com.comye1.catcat.repository.Repository

class CatCatApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)
        Log.d("cca", "repository initialized")
    }
}