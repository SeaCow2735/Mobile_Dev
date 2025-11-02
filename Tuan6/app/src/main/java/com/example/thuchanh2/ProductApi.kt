package com.example.thuchanh2.data

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface ProductApi {
    //GET https://mock.apidog.com/m1/890655-872447-default/v2/product
    @GET("product")
    suspend fun getProduct(): Product

    // Nếu cần lấy theo id: GET https://mock.apidog.com/m1/890655-872447-default/v2/product{id}
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): Product

    companion object {
        private const val BASE_URL = "https://mock.apidog.com/m1/890655-872447-default/v2/"

        fun create(): ProductApi {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            val contentType = "application/json".toMediaType()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()) //
                .build()
                .create(ProductApi::class.java)
        }
    }
}
