package com.kotlin.restful.auth

import com.kotlin.restful.error.UnauthorizedException
import com.kotlin.restful.repository.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

/**
 * Interceptor gunanya adalah mengecek dulu apabila tidak bisa melewati interceptor, maka controller tidak akan dijalankan
 * kita inject api repository kedalam sini
 */

@Component
class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository) : WebRequestInterceptor{

    override fun preHandle(request: WebRequest) {
        // dieksekusi sebelum controller dijalankan
        // kita hanya butuh preHandler untuk mengecek controller sebelum dieksekusi
        val apiKey = request.getHeader("X-Api-Key")

        // jika api key adalah null maka throw exception
        if (apiKey == null) {
            throw UnauthorizedException()
        }

        // kita cek api key nya ada didalam database, jika ada maka valid, dan kalau tidak ada maka tidak valid
        if (!apiKeyRepository.existsById(apiKey)) {
            throw UnauthorizedException()
        }

        // selain diatas maka valid
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        // dieksekusi setelah controller dijalankan
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        // dieksekusi setelah semua selesai dijalankan termasuk render view
    }
}