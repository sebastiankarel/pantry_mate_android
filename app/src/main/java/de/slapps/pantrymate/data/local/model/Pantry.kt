package de.slapps.pantrymate.data.local.model

import kotlinx.serialization.Serializable

@Serializable
data class Pantry(
    val id: String,
    val name: String,
    val description: String,
    val items: List<String>
)