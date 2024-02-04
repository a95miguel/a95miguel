package com.medel.convertidordivisas.Data.Network

import com.medel.convertidordivisas.Data.Database.Model.ResponseBmx
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BmxApiClient {
    @Headers("Accept: application/json")
    @GET("series/{idSerie}/datos/oportuno?token=d77d948bec6538cedc0f6a2b2ef38f06be50b9ef71604b02a46e1fbf13593c44")
    suspend fun getBmxInfo(@Path("idSerie") idSerie : String) : Response<ResponseBmx>

}