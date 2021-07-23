package com.kotlin.restful.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * ini representasi dari table di database
 */
@Entity
@Table(name ="products")
data class Product(

    // kasih tau bahwa id adalah primary key dengan @Id
    @Id
    val id: String,

    @Column(name = "Name")
    val name: String,

    @Column(name = "Price")
    val price: Long,

    @Column(name = "Quantity")
    val quantity: Int,

    @Column(name = "Created_At")
    val createdAt: Date,

    @Column(name = "Updated_At")
    // ? artinya tidak wajib ada datanya atau nullable
    val updatedAt: Date?
)
