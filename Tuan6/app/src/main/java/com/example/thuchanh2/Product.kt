package com.example.thuchanh2.data

import kotlinx.serialization.Serializable


@Serializable
data class Product(
    val imgURL: String,
    val name: String,
    val price: Double,
    val des: String,
    val id: String
)
