package com.example.fakestore.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AccountInformationCard(username: String, email: String,){
    Card(modifier = Modifier) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            Text("Address Information", style = MaterialTheme.typography.titleMedium)
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            HorizontalDivider()
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            Row {
                Column {
                    Text("Username: ", style = MaterialTheme.typography.labelMedium)
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Text("Email: ", style = MaterialTheme.typography.labelMedium)
                }
                Spacer(
                    modifier = Modifier.width(16.dp)
                )
                Column {
                    Text(username, style = MaterialTheme.typography.labelMedium)
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Text(email, style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }

}