package de.slapps.pantrymate.ui.viewmodels

import androidx.lifecycle.ViewModel
import de.slapps.pantrymate.data.PantryRepository
import de.slapps.pantrymate.data.local.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

class PantryContentViewModel(
    private val pantryRepository: PantryRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items = _items.asStateFlow()

    init {
        // Create dummy data
        _items.value = listOf(
            Item("1", "Apples", "Fresh and crispy", 1.0, "kg", Date().time, Date().time + 604800000, "https://via.placeholder.com/150/FF0000/FFFFFF?Text=Apples"),
            Item("2", "Milk", "Whole milk", 1.0, "L", Date().time, Date().time + 432000000, "https://via.placeholder.com/150/FFFFFF/000000?Text=Milk"),
            Item("3", "Bread", "Sourdough", 1.0, "loaf", Date().time, Date().time + 259200000, "https://via.placeholder.com/150/DEB887/000000?Text=Bread"),
            Item("4", "Eggs", "Free-range", 12.0, "pcs", Date().time, Date().time + 1209600000, "https://via.placeholder.com/150/F0E68C/000000?Text=Eggs")
        )
    }

    fun onItemClick(item: Item) {
        // TODO: Handle item click
    }
}