package com.kotlin.restful.model

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateProductRequest(

    /**
     * ? artinya adalah Nullable
     * tambahkan validation untuk masing-masing fieldnya
     * NotBlank artinya tidak boleh ada string kosong
     * NotNull artinya tidak boleh datanya null
     */

    @field:NotBlank
    val id: String?,

    @field:NotBlank
    val name: String?,

    @field:NotNull
    @field:Min(value = 1)
    val price: Long?,

    @field:NotNull
    @field:Min(value = 0)
    val quantity: Int?

)
