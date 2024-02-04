package com.medel.convertidordivisas.Data.Ui.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.medel.convertidordivisas.Data.Database.Model.*
import com.medel.convertidordivisas.Data.Network.BmxApiClient
import com.medel.convertidordivisas.Data.Network.CheckInternet
import com.medel.convertidordivisas.Data.RetrofitHelper
import com.medel.convertidordivisas.Ui.View.CheckInternetActivity
import com.medel.convertidordivisas.databinding.ActivityResultBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val retrofit = RetrofitHelper.getRetrifit()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra("idSerie").toString()

        getBmxInfo(id)
        binding.btnReCalculator.setOnClickListener {
            startActivity(Intent(this, ConvertActivity::class.java))
        }
        checkForInternet()
    }

    private fun getBmxInfo(id: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val call =retrofit.create(BmxApiClient::class.java).getBmxInfo(id)
            if (call.body() != null) {
                //Log.i("medel",call.body().toString())
                runOnUiThread {
                    checkForInternet()
                    createUI(call.body()!!)
                    binding.progressBar.isVisible = false
                    binding.imageView.isVisible = true
                    binding.cardView.isVisible = true
                    binding.btnReCalculator.isVisible = true
                    }
                }
           }
        }


    private fun createUI(response: ResponseBmx) {
        bmxData(response.bmx)
    }
    private fun bmxData(resialbmx: Bmx?){
        seriesData(resialbmx?.series)
    }
    private fun seriesData(seriesItem: List<SeriesItem?>?){
        seriesItem?.forEach {
            datosData(it?.datos)
            binding.tvTitulo.text = it?.titulo.toString()
            Log.i("medel", it?.titulo.toString())
        }
    }
    private fun datosData(datosItem: List<DatosItem?>?){
        datosItem?.forEach {
            val mout = intent.getStringExtra("mout").toString()
            val convertMout : Double= mout.toDouble()
            val conver = it?.dato.toString()
            val doubleVal :Double = conver.toDouble()
            val multConvert=convert(convertMout,doubleVal)

            val df = DecimalFormat("#.##")
            val res = df.format(multConvert)
            val resMout = df.format(doubleVal)
            binding.tvresultMout.text = "$"+res.toString()
            //Log.i("medel",resMout.toString())
            binding.tvDato.text = resMout.toString()
            binding.tvdate.text =it?.fecha.toString()
        }
    }

    private fun convert(mout1: Double,  mout2:Double): Double {
        return mout1*mout2
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
