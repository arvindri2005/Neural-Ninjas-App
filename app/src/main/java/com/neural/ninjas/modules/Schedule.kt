package com.neural.ninjas.modules

data class Schedule(
    val medicineName: String,
    val time: String,
    val status: Boolean = false,
    val medicineAmount: String,
    val type: String
    )
