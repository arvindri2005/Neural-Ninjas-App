package com.neural.ninjas.screens.chat

import com.neural.ninjas.modules.ChatRequest
import com.neural.ninjas.modules.ChatResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("chat/completions")
    suspend fun getChatResponse(
        @Header("Content-Type") contentType: String,
        @Header("Accept") accept: String,
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): ChatResponse

}