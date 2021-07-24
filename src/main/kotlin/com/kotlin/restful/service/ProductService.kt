package com.kotlin.restful.service

import com.kotlin.restful.model.CreateProductRequest
import com.kotlin.restful.model.ListProductRequest
import com.kotlin.restful.model.ProductResponse
import com.kotlin.restful.model.UpdateProductRequest

interface ProductService {

    // function untuk Create Product
    fun create(createProductRequest: CreateProductRequest): ProductResponse

    // function untuk Get Product
    fun get(id: String): ProductResponse

    // function untuk Update Product
    fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse

    // function untuk Delete Product
    fun delete(id: String)

    // function untuk List Product
    fun list(listProductReques: ListProductRequest): List<ProductResponse>

}