package com.medel.convertidordivisas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.medel.convertidordivisas.Model.Intro
import com.medel.convertidordivisas.Data.Ui.View.ConvertActivity
import com.medel.convertidordivisas.Data.Ui.ViewModel.ViewPager
import com.medel.convertidordivisas.databinding.ActivityMainBinding

/**
 * Onboarding walkthrouch es utilizado para introducir al usuario las funciones de la aplicacion
 * Implementado con viewPager2. Se añade en el gradle la implementacion ->implementation 'com.google.android.material:material:1.8.0
 * Para usar vectores se añade una biblioteca para soporte en el build ->  vectorDrawables.useSupportLibrary = true
 * https://youtu.be/Bv6KXIFC7zU
 */
class MainActivity : AppCompatActivity() , ViewPager.OnItemSelected{
    private lateinit var binding :  ActivityMainBinding
    private lateinit var introList: List<Intro>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hacer que una actividad aparezca solo una vez en Android
        val prevStarted = "Yes"
        val name_app = getString(R.string.app_name)
        val sharePreferences=getSharedPreferences(name_app,Context.MODE_PRIVATE)
        if(!sharePreferences.getBoolean(prevStarted,false)){
            val editor:SharedPreferences.Editor =  sharePreferences.edit()
            editor.putBoolean(prevStarted,true)
            editor.apply()
        }else{
            navegateToConvert()
        }
        //Para ocultar el status bar
        /*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
            )
        }*/

        introList = listOf(
            Intro(
                background = R.color.oneView,
                image = R.drawable.ic_calculator,
                title = getString(R.string.title_one),
                subtitle = getString(R.string.subtitle_one)
            ),
            Intro(
                background = R.color.twoView,
                image = R.drawable.ic_eligemoneda,
                title = getString(R.string.title_two),
                subtitle = getString(R.string.subtitle_two)
            ),
            Intro(
                background = R.color.threeView,
                image = R.drawable.ic_confirmed,
                title = getString(R.string.title_three),
                subtitle = getString(R.string.subtitle_three)
            )
        )
        val adapter = ViewPager(introList,this)
        binding.viewPager.adapter = adapter
    }


    override fun onClickListener(position: Int) {
        if(position == (introList.size -1)){
            //Toast.makeText(this,"Agregar activity",Toast.LENGTH_SHORT).show()
            navegateToConvert()
        }else{
            binding.viewPager.setCurrentItem((position + 1),true)
        }
    }

    private fun navegateToConvert(){
        startActivity(Intent(this,ConvertActivity::class.java))
    }

}