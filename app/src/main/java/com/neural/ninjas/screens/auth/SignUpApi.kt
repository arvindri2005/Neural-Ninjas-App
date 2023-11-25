package com.neural.ninjas.screens.auth

import com.neural.ninjas.modules.Login
import com.neural.ninjas.modules.LoginResponse
import com.neural.ninjas.modules.SignUp
import com.neural.ninjas.modules.SignUpResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {
    @POST("/api/user")
    suspend fun signUp(@Body signUp: SignUp): SignUpResponse

}

object SignUpApiCall{
    private const val BASE_URL = "https://neural-mfxu.onrender.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signUpApi: SignUpApi = retrofit.create(SignUpApi::class.java)
}