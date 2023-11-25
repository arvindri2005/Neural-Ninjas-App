package com.neural.ninjas.modules

data class SignUp (
    val name: String,
    val email: String,
    val password: String,
    val pic: String
)

data class SignUpResponse(
    val _id: String="",
    val name: String,
    val email: String,
    val pic: String,
    val token:String,
    val message: String
)