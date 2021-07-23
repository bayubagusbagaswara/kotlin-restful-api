package com.kotlin.restful.controller

import com.kotlin.restful.model.CreateProductRequest
import com.kotlin.restful.model.ProductResponse
import com.kotlin.restful.model.WebResponse
import com.kotlin.restful.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * untuk menandai ini adalah restful api gunakan @RestController
 * kita inject service ke sini
 */

@RestController
class ProductController(val productService: ProductService) {

    /**
     * perlu menambahkan body juga
     */
    @PostMapping(
        value = ["/api/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        // pastikan controller ini manggil service
        val productResponse = productService.create(body);

        // balikan responsenya sebagai WebResponse
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )

    }

}