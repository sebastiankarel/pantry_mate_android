package de.slapps.pantrymate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import de.slapps.pantrymate.data.local.model.Item
import de.slapps.pantrymate.ui.viewmodels.ItemDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ItemDetailsScreen(viewModel: ItemDetailsViewModel = koinViewModel()) {
    val item by viewModel.item.collectAsState()
    item?.let { 
        ItemDetailsScreenContent(item = it)
    }
}

@Composable
private fun ItemDetailsScreenContent(item: Item) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        item.imageUrl?.let {
            AsyncImage(
                model = it,
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(bottom = 16.dp)
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = item.description,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(text = "Quantity: ${item.quantity} ${item.quantityUnit}")
            Text(text = "Created: ${formatDate(item.creationDate)}")
            Text(text = "Expires: ${formatDate(item.expiryDate)}")
        }
    }
}

private fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

@Preview
@Composable
private fun ItemDetailsScreenPreview() {
    val item = Item(
        id = "1",
        name = "Apples",
        description = "Fresh and crispy red apples, perfect for a healthy snack or for baking.",
        quantity = 1.0,
        quantityUnit = "kg",
        creationDate = Date().time,
        expiryDate = Date().time + 604800000, // 1 week expiry
        imageUrl = "https://via.placeholder.com/300/FF0000/FFFFFF?Text=Apples"
    )
    ItemDetailsScreenContent(item = item)
}
