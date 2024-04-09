package com.medel.vivero_v1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Se añade a manifest android:name=".MyApp"
@HiltAndroidApp
class MyApp : Application()