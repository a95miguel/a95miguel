package com.example.vivero

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application()
//En el Manifest se añade android:name=".MyApp"