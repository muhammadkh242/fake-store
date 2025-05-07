package com.example.fakestore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.fakestore.R

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    count: Int,
    spaceBetween: Dp = 0.dp
) {
    val emptyStar = painterResource(id = R.drawable.star)
    val fullStar = painterResource(id = R.drawable.star_full)

    val totalCount = 5

    // Get intrinsic size of the painter
    val starSize = emptyStar.intrinsicSize
    val height = with(LocalDensity.current) { starSize.height.toDp() }
    val width = with(LocalDensity.current) { starSize.width.toDp() }

    val totalWidth = width * totalCount + spaceBetween * (totalCount - 1)
    val density = LocalDensity.current

    Row {
        Row(
            modifier = modifier
                .width(totalWidth)
                .height(height),
            horizontalArrangement = Arrangement.spacedBy(spaceBetween)
        ) {
            for (i in 0 until totalCount) {
                Box(
                    modifier = Modifier
                        .width(width)
                        .fillMaxHeight()
                ) {
                    // Draw empty star
                    Image(painter = emptyStar, contentDescription = "Empty star")

                    // Draw filled star with clip based on rating
                    if (rating > i) {
                        val clipWidth = if (rating < i + 1) {
                            with(density) { (rating - i) * width.toPx() }
                        } else {
                            with(density) { width.toPx() }
                        }

                        Image(
                            painter = fullStar,
                            contentDescription = "Filled star",
                            modifier = Modifier.clip(
                                ClipRect(
                                    left = 0f,
                                    top = 0f,
                                    right = clipWidth,
                                    bottom = with(density) { height.toPx() }
                                )
                            )
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(8.dp))
        Text(
            "(${count} reviews)",
        )
    }
}

// Helper class for custom clipping
class ClipRect(val left: Float, val top: Float, val right: Float, val bottom: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            androidx.compose.ui.geometry.Rect(
                left = left,
                top = top,
                right = right,
                bottom = bottom
            )
        )
    }
}