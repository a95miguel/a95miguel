package com.medel.convertidordivisas.Data.Network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData

class CheckInternet (context: Context): LiveData<Boolean>() {

        //Como usar https://www.geeksforgeeks.org/how-to-check-internet-connection-in-kotlin/
        private val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallbacks = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(false)
        }
    }
    private fun checkInternet(){
        val network = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork
        } else {
            ////////////////////////////////////////////////////////////77
           // @Suppress("DEPRECATION") val networkInfo =  connectivityManager.activeNetworkInfo
            val check = connectivityManager.activeNetworkInfo
            check?.isConnected
        }
        if(network==null){
            postValue(false)
        }

        /**
         * Después de verificar la red, es hora de verificar las capacidades de Internet de la red, ya sea que la conexión tenga Internet o no,
         * para eso registraremos la red y luego verificaremos las capacidades de la red con la ayuda de las devoluciones de llamada.
         */
        val requestBuilder = NetworkRequest.Builder().apply {
            addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        }.build()

        connectivityManager.registerNetworkCallback(requestBuilder,networkCallbacks)
    }

    override fun onActive() {
        super.onActive()
        checkInternet()
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallbacks)
    }
}

