package com.example.fakestore.ui.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.fakestore.data.model.Product
import com.example.fakestore.ui.theme.Pink80

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Pink80,
        ),
        shape = RoundedCornerShape(size = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Card(
                shape = RoundedCornerShape(8.dp)
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.description,
                    modifier = Modifier.size(120.dp),
                )
            }
            Text(
                product.title,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                product.description,
                style = MaterialTheme.typography.bodyMedium

            )
        }
    }

}

