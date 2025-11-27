package de.slapps.pantrymate.data.local.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: String,
    val name: String,
    val description: String,
    val quantity: Double,
    val quantityUnit: String,
    val creationDate: Long,
    val expiryDate: Long,
    val imageUrl: String? = null
)