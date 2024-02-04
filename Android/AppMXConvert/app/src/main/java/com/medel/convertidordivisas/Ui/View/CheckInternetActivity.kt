package com.medel.convertidordivisas.Ui.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.medel.convertidordivisas.Data.Network.CheckInternet
import com.medel.convertidordivisas.Data.Ui.View.ConvertActivity
import com.medel.convertidordivisas.Data.Ui.View.ResultActivity
import com.medel.convertidordivisas.databinding.ActivityCheckInternetBinding

class CheckInternetActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCheckInternetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)
            binding.btnRefresh.setOnClickListener {
                startActivity(Intent(this,ConvertActivity::class.java))
            }
        }

}