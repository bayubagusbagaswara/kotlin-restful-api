package com.kotlin.restful.controller

import com.kotlin.restful.model.*
import com.kotlin.restful.service.ProductService
import org.springframework.web.bind.annotation.*

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

    /**
     * controller untuk Get Product
     */
    @GetMapping(
        value = ["/api/products/{idProduct}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("idProduct") id: String): WebResponse<ProductResponse> {
        val productResponse = productService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    /**
     * controller untuk Update Product
     */

    @PutMapping(
        value = ["/api/products/{idProduct}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateProduct(@PathVariable("idProduct") id: String,
                      @RequestBody updateProductRequest: UpdateProductRequest): WebResponse<ProductResponse> {

        val productResponse = productService.update(id, updateProductRequest)

        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    /**
     * controller untuk Delete Product
     */
    @DeleteMapping(
        value = ["/api/products/{idProduct}"],
        produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("idProduct") id: String): WebResponse<String> {
        productService.delete(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = id
        )
    }

    /**
     * controller untuk List Product
     * tambahkan @RequestParam untuk memberitahu bahwa size dan page dimasukkan melalui query param
     */
    @GetMapping(
        value = ["/api/products"],
        produces = ["application/json"]
    )
    fun listProducts(@RequestParam(value = "size", defaultValue = "10") size: Int,
                     @RequestParam(value = "page", defaultValue = "0") page: Int): WebResponse<List<ProductResponse>> {
        val request = ListProductRequest(page = page, size = size)
        val responses = productService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }

}