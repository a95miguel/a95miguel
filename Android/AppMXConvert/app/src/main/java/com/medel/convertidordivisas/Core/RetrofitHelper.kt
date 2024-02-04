package com.medel.convertidordivisas.Data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {
    fun getRetrifit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.banxico.org.mx/SieAPIRest/service/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}