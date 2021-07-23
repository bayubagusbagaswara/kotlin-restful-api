package com.kotlin.restful.service

import com.kotlin.restful.model.CreateProductRequest
import com.kotlin.restful.model.ProductResponse

interface ProductService {

    fun create(createProductRequest: CreateProductRequest): ProductResponse

}