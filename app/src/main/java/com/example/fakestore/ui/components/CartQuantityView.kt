package com.example.fakestore.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fakestore.R

@Composable
fun CartQuantityView() {
    var count by rememberSaveable { mutableIntStateOf(1) }

    Row {
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(
                    enabled = count > 1,
                    onClick = {
                        count--
                    },
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(R.drawable.ic_minus), contentDescription = null)
        }
        Box(
            modifier = Modifier
                .size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "$count",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable(
                    enabled = count < 10,
                    onClick = {
                        count++

                    },
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}