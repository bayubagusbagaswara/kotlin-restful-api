package com.kotlin.restful.service.impl

import com.kotlin.restful.entity.Product
import com.kotlin.restful.model.CreateProductRequest
import com.kotlin.restful.model.ProductResponse
import com.kotlin.restful.repository.ProductRepository
import com.kotlin.restful.service.ProductService
import org.springframework.stereotype.Service
import java.util.*

/**
 * set sebagai Service dengan menggunakan @Service
 * kita inject Product Repository kedalam sini
 */

@Service
class ProductServiceImpl(val productRepository: ProductRepository) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        // kita insert data product nya ke dalam database
        val product = Product(
            id = createProductRequest.id,
            name = createProductRequest.name,
            price = createProductRequest.price,
            quantity = createProductRequest.quantity,
            createdAt = Date(),
            updatedAt = null
        )

        // set ke database melalui repository
        productRepository.save(product)

        // balikan responsenya
        return  ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }

}