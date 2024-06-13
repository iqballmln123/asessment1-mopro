package org.d3if3056.assesment.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3056.assesment.model.Skincare
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" + "indraazimi/mobpro1-compose/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SkincareApiService {
    @GET("static-api.json")
    suspend fun getSkincare(): List<Skincare>
}

object SkincareApi {
    val service: SkincareApiService by lazy {
        retrofit.create(SkincareApiService::class.java)
    }

    fun getSkincareUrl(imageId: String): String{
        return "$BASE_URL$imageId.jpg"
    }
}