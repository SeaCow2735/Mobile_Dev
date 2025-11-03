package com.example.smarttasksapp

import com.example.smarttasksapp.TaskDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface TasksAPI {
    @GET("tasks")
    suspend fun getTasks(): ApiResponse<List<TaskDto>>

    @GET("task/{id}")
    suspend fun getTaskById(@Path("id") id: Int): ApiResponse<TaskDto>

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): ApiResponse<Unit?>

    companion object {
        private const val BASE_URL = "https://amock.io/api/researchUTH/"

        fun create(): TasksAPI {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(TasksAPI::class.java)
        }
    }
}



