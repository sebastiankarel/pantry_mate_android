package de.slapps.pantrymate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.slapps.pantrymate.data.local.model.Pantry
import de.slapps.pantrymate.ui.viewmodels.PantrySelectionViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PantrySelectionScreen(viewModel: PantrySelectionViewModel = koinViewModel()) {
    val pantries by viewModel.pantries.collectAsState()
    PantrySelectionScreenContent(pantries = pantries, onPantryClick = viewModel::onPantryClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PantrySelectionScreenContent(pantries: List<Pantry>, onPantryClick: (Pantry) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(pantries) { pantry ->
            Card(
                onClick = { onPantryClick(pantry) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = pantry.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = pantry.description,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PantrySelectionScreenPreview() {
    val pantries = listOf(
        Pantry("1", "Kitchen Pantry", "All the dry goods and canned food. This is a longer description to test text overflow.", emptyList()),
        Pantry("2", "Fridge", "Fresh produce, dairy, and leftovers.", emptyList()),
        Pantry("3", "Freezer", "Frozen items.", emptyList()),
        Pantry("4", "Spice Rack", "All the spices and herbs for your favorite dishes.", emptyList()),
    )
    PantrySelectionScreenContent(pantries = pantries, onPantryClick = {})
}
