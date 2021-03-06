package com.kotlin.restful.service.impl

import com.kotlin.restful.entity.Product
import com.kotlin.restful.error.NotFoundException
import com.kotlin.restful.model.CreateProductRequest
import com.kotlin.restful.model.ListProductRequest
import com.kotlin.restful.model.ProductResponse
import com.kotlin.restful.model.UpdateProductRequest
import com.kotlin.restful.repository.ProductRepository
import com.kotlin.restful.service.ProductService
import com.kotlin.restful.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

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
        val product = findProductByIdOrThrowNotFound(id)
        return convertProductToProductResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        // jika id nya tidak ketemu, maka lempar NotFoundException
        val product = findProductByIdOrThrowNotFound(id)
        // lakukan validation dulu sebelum update data product
        validationUtil.validate(updateProductRequest)

        // jika id nya ada, maka set dengan data baru
        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            // kita perlu tambahkan waktu updatenya
            updatedAt = Date()
        }
        // lakukan save untuk update data product yang baru
        productRepository.save(product)
        // balikan data response product
        return convertProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductByIdOrThrowNotFound(id)
        // lalu delete menggunakan repository
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        // pageable balikannya ada parameter paging nya
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        // page balikannya adalah stream product, dan lalu konversi menjadi list menggunakan collect
        // hasil dari product adalah List of product
        val products = page.get().collect(Collectors.toList())
        // transform menjadi product response
        return products.map { convertProductToProductResponse(it) }
    }

    // function untuk mengecek id, jika id tidak ditemukan maka lempar NotFoundException
    private fun findProductByIdOrThrowNotFound(id: String): Product {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw NotFoundException()
        } else {
            return product
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