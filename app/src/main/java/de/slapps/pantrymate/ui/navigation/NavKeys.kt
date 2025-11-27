package de.slapps.pantrymate.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Login : NavKey

@Serializable
data object PantrySelection : NavKey

@Serializable
data class PantryContent(
    val pantryId: String,
    val pantryName: String,
) : NavKey

@Serializable
data class ItemDetails(
    val itemId: String,
    val itemName: String,
) : NavKey