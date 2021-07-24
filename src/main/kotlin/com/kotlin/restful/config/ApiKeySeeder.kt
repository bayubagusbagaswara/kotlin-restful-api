package com.kotlin.restful.config

import com.kotlin.restful.entity.ApiKey
import com.kotlin.restful.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

/**
 * applicationRunner adalah salah satu buah class yang dimana ia akan dieksekusi ketika si program spring itu berjalan
 * jadi pertama kali spring  berjalan, ini akan dieksekusi
 * kita inject juga repository unutk api key nya
 */

@Component
class ApiKeySeeder(val apiKeyRepository: ApiKeyRepository) : ApplicationRunner{
    // kita kan insert api key nya secara otomatis disini

    // buat api key nya
    val apiKey = "SECRET"

    override fun run(args: ApplicationArguments?) {
        // kita cek api key nya
        if (!apiKeyRepository.existsById(apiKey)) {
            val entity = ApiKey(id = apiKey)
            apiKeyRepository.save(entity)
        }
    }
}