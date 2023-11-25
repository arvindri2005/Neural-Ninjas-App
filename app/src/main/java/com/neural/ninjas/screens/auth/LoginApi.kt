package com.neural.ninjas.screens.auth

import com.neural.ninjas.modules.Login
import com.neural.ninjas.modules.LoginResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("/api/user/login")
    suspend fun login(@Body login: Login): LoginResponse
}

object LoginApiCall{
    private const val BASE_URL = "https://neuralninjas.onrender.com"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val loginApi: LoginApi = retrofit.create(LoginApi::class.java)

}