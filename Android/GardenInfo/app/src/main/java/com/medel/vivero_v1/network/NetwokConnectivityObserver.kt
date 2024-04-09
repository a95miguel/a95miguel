package com.example.vivero.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/**
 * Implementa la interfaz [ConnectivityObserver] para observar el estado de conexion de la red.
 *
 * @param context El contexto de la alicacion.
 */
class NetwokConnectivityObserver(
    context : Context
) :ConnectivityObserver{
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    /**
     * Con Flow emite el estado actual de la conexion del internet
     *
     * @return [Flow] emite los valores de [ConnectivityObserver.Status] indicando el estado de la red.
     */
    override fun observer(): Flow<ConnectivityObserver.Status> {
        return callbackFlow{
            val callback = object : ConnectivityManager.NetworkCallback(){
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(ConnectivityObserver.Status.Available) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(ConnectivityObserver.Status.Losing) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(ConnectivityObserver.Status.Lost) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(ConnectivityObserver.Status.Unavailable) }
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    if (!networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                        launch { send(ConnectivityObserver.Status.Limited) }
                    }
                }

            }
            //Registra el callback para observar los cambios
            connectivityManager.registerDefaultNetworkCallback(callback)
            //Cierra el flujo al registrar el callback cuando no se necesite.
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}