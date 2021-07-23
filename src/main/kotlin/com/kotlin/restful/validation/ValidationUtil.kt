package com.kotlin.restful.validation

import org.springframework.stereotype.Component
import javax.validation.ConstraintViolationException
import javax.validation.Validator

/**
 * ini berfungsi sebagai validation yang biasanya untuk melakukan sebuah validation itu terdiri dari beberapa object
 */

@Component
class ValidationUtil(val validator: Validator) {

    fun validate(any: Any) {
        val result = validator.validate(any)
        if (result.size != 0) {
            // maka terjadi error
            throw ConstraintViolationException(result)
        }
    }
}