package com.kotlin.restful.repository

import com.kotlin.restful.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

/**
 * layer repository ini extends JPA Repository, dimana membutuhkan entity Product dan id String
 */
interface ProductRepository : JpaRepository<Product, String>{
}