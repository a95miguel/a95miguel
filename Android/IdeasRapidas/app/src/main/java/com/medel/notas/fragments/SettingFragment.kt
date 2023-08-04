package com.medel.notas.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.medel.notas.R
import com.medel.notas.databinding.FragmentSettingBinding
import com.medel.notas.network.NightModeHelper

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        // Inicialización de Firebase Authentication.
        auth = FirebaseAuth.getInstance()
        // Verificamos si hay un usuario actualmente autenticado (registrado) en Firebase Authentication.
        if (auth.currentUser == null) {
            navController.navigate(R.id.signFragment)
        }
        nightMode(requireContext())
        registerEvents()
    }

    private fun nightMode(context: Context) {
        // Obtener el estado actual del modo nocturno desde las preferencias compartidas
        val isNightModeActive = NightModeHelper.getNightMode(context)

        // Reflejar el estado del modo nocturno en el Switch
        binding.switchDarkMode.isChecked = isNightModeActive

        binding.switchDarkMode.setOnCheckedChangeListener { button, isChecked ->
            NightModeHelper.setNightMode(context, isChecked)

            // Cambiar el modo nocturno según el estado del switch
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }


    private fun registerEvents() {
        binding.btnClose.setOnClickListener {
            // Desconexión actual de Firebase Authentication mediante el método "signOut()"
            auth.signOut()
            navController.navigate(R.id.action_settingFragment_to_signFragment)
        }
    }

}
