package de.slapps.pantrymate.ui.viewmodels

import androidx.lifecycle.ViewModel
import de.slapps.pantrymate.data.PantryRepository
import de.slapps.pantrymate.data.local.model.Pantry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PantrySelectionViewModel(
    private val pantryRepository: PantryRepository
) : ViewModel() {

    private val _pantries = MutableStateFlow<List<Pantry>>(emptyList())
    val pantries = _pantries.asStateFlow()

    init {
        // Create dummy data
        _pantries.value = listOf(
            Pantry("1", "Kitchen Pantry", "All the dry goods and canned food.", emptyList()),
            Pantry("2", "Fridge", "Fresh produce, dairy, and leftovers.", emptyList()),
            Pantry("3", "Freezer", "Frozen items.", emptyList()),
            Pantry("4", "Spice Rack", "All the spices.", emptyList()),
        )
    }

    fun onPantryClick(pantry: Pantry) {
        // TODO: Handle pantry click
    }
}