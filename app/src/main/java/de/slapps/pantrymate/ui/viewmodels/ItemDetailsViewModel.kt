package de.slapps.pantrymate.ui.viewmodels

import androidx.lifecycle.ViewModel
import de.slapps.pantrymate.data.PantryRepository
import de.slapps.pantrymate.data.local.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class ItemDetailsViewModel(
    private val pantryRepository: PantryRepository
) : ViewModel() {

    private val _item = MutableStateFlow<Item?>(null)
    val item = _item.asStateFlow()

    init {
        // Create dummy data
        _item.value = Item(
            id = "1",
            name = "Apples",
            description = "Fresh and crispy red apples, perfect for a healthy snack or for baking.",
            quantity = 1.0,
            quantityUnit = "kg",
            creationDate = Date().time,
            expiryDate = Date().time + 604800000, // 1 week expiry
            imageUrl = "https://via.placeholder.com/300/FF0000/FFFFFF?Text=Apples"
        )
    }
}