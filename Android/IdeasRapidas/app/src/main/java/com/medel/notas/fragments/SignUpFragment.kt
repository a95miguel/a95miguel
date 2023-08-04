package com.medel.notas.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.medel.notas.R
import com.medel.notas.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var auth : FirebaseAuth
    private lateinit var navContrl : NavController
    private lateinit var binding : FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // se utiliza para establecer la orientación de la actividad actual a "vertical"
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        init(view)
        registerEvents()
    }

    private fun init(view: View) {
        navContrl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    private fun registerEvents() {
        binding.tvAuth.setOnClickListener {
            navContrl.navigate(R.id.action_signUpFragment_to_signFragment)
        }

        binding.btnNext.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etpassword.text.toString().trim()
            val verifyPass = binding.etrepassword.text.toString().trim()
            // Verificamos que los campos que no estén vacíos.
            if(email.isNotEmpty() && pass.isNotEmpty() && verifyPass.isNotEmpty()){
                // Verificamos si las contraseñas coinciden.
                if(pass == verifyPass){
                    binding.progressBar.visibility = View.VISIBLE
                    // Creamos un nuevo usuario en Firebase Authentication con el email y contraseña proporcionados.
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(
                        OnCompleteListener {
                            // Si el registro fue exitoso, mostramos un Toast de éxito y navegamos hacia el fragmento
                            if(it.isSuccessful){
                                Toast.makeText(context,R.string.sucessful,Toast.LENGTH_SHORT).show()
                                navContrl.navigate(R.id.action_signUpFragment_to_homeFragment2)
                            }else{
                                // Si el registro falló, obtenemos el mensaje de error proporcionado por Firebase Authentication.
                                val message = it.exception?.message
                                if(message=="The email address is already in use by another account."){
                                    Toast.makeText(context,R.string.error_email_already,Toast.LENGTH_SHORT).show()
                                }
                                if(message=="The given password is invalid. [ Password should be at least 6 characters ]"){
                                    Toast.makeText(context,R.string.error_campos,Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(context,it.exception?.message , Toast.LENGTH_SHORT).show()
                                }
                            }
                            binding.progressBar.visibility = View.GONE
                        }
                    )
                }else{
                    Toast.makeText(context,R.string.password_erros,Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context,R.string.verefy_fields,Toast.LENGTH_SHORT).show()
            }
        }
    }
}