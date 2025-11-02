package com.example.thuchanh2

import com.example.thuchanh2.data.Product
import com.example.thuchanh2.data.ProductApi

class ProductRepository(
    private val api: ProductApi = ProductApi.create()
) {
    suspend fun fetchProduct(): Result<Product> = runCatching { api.getProduct() }
    suspend fun fetchProductById(id: String): Result<Product> = runCatching { api.getProductById(id) }
}
