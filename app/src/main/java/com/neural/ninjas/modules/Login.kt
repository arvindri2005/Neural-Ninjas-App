package com.neural.ninjas.modules

data class Login(
    val email: String,
    val password: String
)

data class LoginResponse(
    val _id: String,
    val name: String,
    val email: String,
    val pic: String,
    val token:String
)