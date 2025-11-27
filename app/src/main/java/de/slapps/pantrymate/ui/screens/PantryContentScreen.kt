package de.slapps.pantrymate.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import de.slapps.pantrymate.data.local.model.Item
import de.slapps.pantrymate.ui.viewmodels.PantryContentViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PantryContentScreen(viewModel: PantryContentViewModel = koinViewModel()) {
    val items by viewModel.items.collectAsState()
    PantryContentScreenContent(items = items, onItemClick = viewModel::onItemClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PantryContentScreenContent(items: List<Item>, onItemClick: (Item) -> Unit) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(items) { item ->
            Card(
                onClick = { onItemClick(item) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = item.description,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Row(modifier = Modifier.padding(top = 8.dp)) {
                            Text(text = "Quantity: ${item.quantity} ${item.quantityUnit}")
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                        Text(text = "Created: ${formatDate(item.creationDate)}")
                        Text(text = "Expires: ${formatDate(item.expiryDate)}")
                    }
                    item.imageUrl?.let {
                        AsyncImage(
                            model = it,
                            contentDescription = item.name,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

@Preview
@Composable
private fun PantryContentScreenPreview() {
    val items = listOf(
        Item("1", "Apples", "Fresh and crispy", 1.0, "kg", Date().time, Date().time + 604800000, "https://via.placeholder.com/150/FF0000/FFFFFF?Text=Apples"),
        Item("2", "Milk", "Whole milk", 1.0, "L", Date().time, Date().time + 432000000, "https://via.placeholder.com/150/FFFFFF/000000?Text=Milk"),
    )
    PantryContentScreenContent(items = items, onItemClick = {})
}
