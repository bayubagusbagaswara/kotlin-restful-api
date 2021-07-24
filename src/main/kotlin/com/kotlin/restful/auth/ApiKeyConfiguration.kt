package com.kotlin.restful.auth

import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * setelah kita membuat interceptor api key, maka lalu kita perlu meregistry kedalam spring nya
 */
@Component
class ApiKeyConfiguration(val apiKeyInterceptor: ApiKeyInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)

        // tinggal kita registry interceptor yang sudah dibuat
        registry.addWebRequestInterceptor(apiKeyInterceptor)
    }
}