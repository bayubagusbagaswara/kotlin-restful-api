package com.kotlin.restful.service

import com.kotlin.restful.model.CreateProductRequest
import com.kotlin.restful.model.ProductResponse

interface ProductService {

    // function untuk Create Product
    fun create(createProductRequest: CreateProductRequest): ProductResponse

    // function untuk Get Product
    fun get(id: String): ProductResponse

}