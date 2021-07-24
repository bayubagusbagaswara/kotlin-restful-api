package com.kotlin.restful.service.impl

import com.kotlin.restful.entity.Product
import com.kotlin.restful.error.NotFoundException
import com.kotlin.restful.model.CreateProductRequest
import com.kotlin.restful.model.ProductResponse
import com.kotlin.restful.repository.ProductRepository
import com.kotlin.restful.service.ProductService
import com.kotlin.restful.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

/**
 * set sebagai Service dengan menggunakan @Service
 * kita inject Product Repository kedalam sini
 */

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
    ) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        // kita lakukan validation dulu, jika terjadi error maka akan throw exception
        validationUtil.validate(createProductRequest)

        // kita insert data product nya ke dalam database
        val product = Product(
            /**
             * !! artinya data tidak boleh null
             */
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!!,
            createdAt = Date(),
            updatedAt = null
        )

        // set ke database melalui repository
        productRepository.save(product)

        // balikan responsenya
        return convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        // kita load dari repository
        val product = productRepository.findByIdOrNull(id)

        // cek prouctnya null atau tidak
        if (product == null) {
            // disini artinya tidak ada datanya di database
            // idealnya balikannya adalah error 404
            throw NotFoundException()
        } else {
            return convertProductToProductResponse(product)
        }
    }

    // function untuk response dari Product
    private fun convertProductToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }

}