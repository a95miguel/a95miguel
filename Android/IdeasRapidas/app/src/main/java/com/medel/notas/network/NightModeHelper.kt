package com.medel.notas.network

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object NightModeHelper {
    private const val PREFS_NAME = "Mode"
    private const val KEY_NIGHT_MODE = "night"

    fun setNightMode(context: Context, isNightMode: Boolean) {
        // Obtiene el objeto SharedPreferences para guardar las preferencias.
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Establece el modo nocturno en función del valor de 'isNightMode'.
        if (isNightMode) {
            // Se activa el modo nocturno.
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            //Se desactiva el modo nocturno
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Guarda el estado del modo nocturno en las preferencias compartidas.
        editor.putBoolean(KEY_NIGHT_MODE, isNightMode)
        editor.apply()
    }

    fun getNightMode(context: Context): Boolean {
        // Obtiene el objeto SharedPreferences para leer las preferencias.
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        // Obtiene el valor del modo nocturno desde las preferencias compartidas.
        // Si no se encuentra la clave, devolverá false por defecto.
        return sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)
    }
}