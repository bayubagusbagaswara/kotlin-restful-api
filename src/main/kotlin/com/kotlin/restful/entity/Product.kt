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
    // var artinya bisa di assign ulang
    @Id
    val id: String,

    @Column(name = "Name")
    var name: String,

    @Column(name = "Price")
    var price: Long,

    @Column(name = "Quantity")
    var quantity: Int,

    @Column(name = "Created_At")
    var createdAt: Date,

    @Column(name = "Updated_At")
    // ? artinya tidak wajib ada datanya atau nullable
    var updatedAt: Date?
)
