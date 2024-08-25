package com.example.androiddrugbugtests.network

import com.example.androiddrugbugtests.models.InteractionsResponseModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://10.0.0.176:8080/warning/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofitMedSearch = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MedSearchTestsApiService {
    @GET("mult")
    suspend fun getInteractionsResponse(
        @Query("interactor") interactor: String
    ): InteractionsResponseModel
}

object MedSearchTestsApi {
    val retrofitService: MedSearchTestsApiService by lazy {
        retrofitMedSearch.create(MedSearchTestsApiService::class.java)
    }
}