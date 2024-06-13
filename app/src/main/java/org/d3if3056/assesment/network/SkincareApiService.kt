package org.d3if3056.assesment.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" + "indraazimi/mobpro1-compose/static-api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface SkincareApiService {
    @GET("static-api.json")
    suspend fun getSkincare(): String
}

object SkincareApi {
    val service: SkincareApiService by lazy {
        retrofit.create(SkincareApiService::class.java)
    }
}