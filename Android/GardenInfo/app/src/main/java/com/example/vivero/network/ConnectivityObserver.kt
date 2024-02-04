package com.example.vivero.network

import kotlinx.coroutines.flow.Flow

/**
 * Define los metodos para observar el estado de conectividad.
 */
interface ConnectivityObserver {
    /**
     * Con el Flow emite el estado actual de la conexion.
     *
     * @return  [Flow] emite los valores  de  [Status] del estado de conectividad.
     */
    fun observer(): Flow<Status>

    /**
     * Enumeracion de los posibles estados de conectividad.
     */
    enum class Status{
        Available, Unavailable, Losing, Lost, None
        //Available-> Conexion disponible
        //Unavailable-> Conexion no esta disponible
        //Losing-> Conexion se esta perdiendo
        //Lost-> Conexion se ha perdido
        //None-> No hay conexion disponible
    }
}