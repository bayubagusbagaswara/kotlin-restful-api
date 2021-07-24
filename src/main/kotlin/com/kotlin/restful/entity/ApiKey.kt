package com.kotlin.restful.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * entity atau representasi data table api_keys dari database
 */
@Entity
@Table(name = "api_keys")
data class ApiKey(

    @Id
    val id: String
)
