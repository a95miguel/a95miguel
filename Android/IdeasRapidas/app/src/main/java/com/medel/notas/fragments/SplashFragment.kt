package com.medel.notas.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.medel.notas.R

class SplashFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        auth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        // Usamos un Handler para realizar una acción después de un retraso de 2 segundos.
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            // Verificamos si hay un usuario actualmente autenticado (registrado) en Firebase Authentication.
            if (auth.currentUser != null) {
                navController.navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                navController.navigate(R.id.action_splashFragment_to_signFragment)
            }
        }, 2000)
    }
}