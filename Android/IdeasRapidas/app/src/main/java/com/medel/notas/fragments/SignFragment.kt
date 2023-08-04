package com.medel.notas.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.medel.notas.R
import com.medel.notas.databinding.FragmentSignBinding

class SignFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navContrl: NavController
    private lateinit var binding: FragmentSignBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // se utiliza para establecer la orientación de la actividad actual a "vertical"
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //navContrl.navigateUp()
        init(view)
        registerEvents()

    }

    private fun init(view: View) {
        navContrl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    private fun registerEvents() {
        binding.tvAuth.setOnClickListener {
            navContrl.navigate(R.id.action_signFragment_to_signUpFragment)
        }

        binding.btnNext.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etpassword.text.toString().trim()
            // Verificamos que los campos de que no estén vacíos.
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                binding.progressBar.visibility = View.VISIBLE
                // Inicia sesión con el correo electrónico y contraseña proporcionados mediante Firebase Authentication.
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                    OnCompleteListener {   // El método addOnCompleteListener se ejecutará cuando la tarea de inicio de sesión se complete.
                        // Verefica si la sesión es exitoso.
                        if (it.isSuccessful) {
                            //Toast.makeText(context,"Login exitosamente", Toast.LENGTH_SHORT).show()
                            navContrl.navigate(R.id.action_signFragment_to_homeFragment)
                        } else {
                            Toast.makeText(context, R.string.error_sesion, Toast.LENGTH_SHORT)
                                .show()
                            //Toast.makeText(context,it.exception?.message , Toast.LENGTH_SHORT).show()
                            //Log.e("medel","{it.exception}")
                        }
                        binding.progressBar.visibility = View.GONE
                    }
                )
            } else {
                Toast.makeText(context, R.string.verefy_fields, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(this){
            // Cierra la aplicación completamente cuando se presiona el botón "Atrás"
            requireActivity().finish()
        }
    }
}