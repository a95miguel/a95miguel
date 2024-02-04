package com.medel.convertidordivisas.Data.Ui.View

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.medel.convertidordivisas.Data.Model.SpinnerData
import com.medel.convertidordivisas.Data.Network.CheckInternet
import com.medel.convertidordivisas.Data.Ui.ViewModel.ItemAdapter
import com.medel.convertidordivisas.R
import com.medel.convertidordivisas.Ui.View.CheckInternetActivity
import com.medel.convertidordivisas.databinding.ActivityConvertBinding
import kotlin.system.exitProcess

class ConvertActivity : AppCompatActivity(){
    private lateinit var binding : ActivityConvertBinding
    var serie : String = ""
    var mout : String  = ""
    var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConvertBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCustomAdapterSpinner()
        binding.btnConvert.setOnClickListener {
            isEmptyValue()
        }
        createDialog()
        checkForInternet()
    }

    private fun isEmptyValue(){
        if(binding.etamout.text.toString().isNotEmpty()){
            mout = binding.etamout.text.toString()
            //val res = checkForInternet.checkForInternet(this)
            navegateToResul(serie,mout)

            /*if(/*checkForInternet(this)*/){
                navegateToResul(serie,mout)
            }else{
                startActivity(Intent(this,CheckInternetActivity::class.java))
            }*/

        }else{
            val toast = Toast.makeText(this@ConvertActivity,R.string.errorValue,Toast.LENGTH_SHORT)
            toast.view?.background?.setTintList(ContextCompat.getColorStateList(this,R.color.oneView))
            toast.show()
        }
    }

    private fun setCustomAdapterSpinner() {
        val pais_list = arrayListOf<SpinnerData>()
        pais_list.add(SpinnerData("SF43718","USD",R.drawable.ic_usa))
        pais_list.add(SpinnerData("SF46410","EUR",R.drawable.ic_europe))
        pais_list.add(SpinnerData("SF46407","GBP",R.drawable.ic_libra))
        pais_list.add(SpinnerData("SF46406","JPY",R.drawable.ic_japan))
        pais_list.add(SpinnerData("SF60632","CAD",R.drawable.ic_canada))

        val adapter = ItemAdapter(this,pais_list)
        binding.spinnerSelect.adapter = adapter

        binding.spinnerSelect.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                //navegateToResul(pais_list.get(pos).idSerie)
                serie=pais_list.get(pos).idSerie
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        })
        adapter.notifyDataSetChanged()
    }

    private fun navegateToResul(idSerie : String,mout : String){
        val intent = Intent(this,ResultActivity::class.java)
        intent.putExtra("idSerie",idSerie)
        intent.putExtra("mout",mout)
        startActivity(intent)
    }

    override fun onBackPressed() {
        alertDialog?.show()
    }

    private fun createDialog(){
        //Test exit app -> https://www.waldo.com/blog/kotlin-alertdialog
        val alertDialogBuider = AlertDialog.Builder(this)
        alertDialogBuider.setTitle(R.string.exit)
        alertDialogBuider.setMessage(R.string.exit_info)
        alertDialogBuider.setPositiveButton(R.string.yes){ _ : DialogInterface, _: Int ->
            finish()
            finishAffinity()
        }
        alertDialogBuider.setNegativeButton("Cancel", { dialogInterface: DialogInterface, i: Int -> })
        alertDialog = alertDialogBuider.create()
    }

    private fun checkForInternet(){
        val connec = CheckInternet(this)
        connec.observe(this, Observer {
            if(!it){
                startActivity(Intent(this,CheckInternetActivity::class.java))
            }
        })
    }
}