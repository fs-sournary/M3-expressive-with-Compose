package com.itlifelang.m3expressivesample.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Item(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    ListItem(
        modifier = modifier.clickable(onClick = onClick),
        headlineContent = { Text(text = title) },
        leadingContent = {
            Icon(imageVector = Icons.Filled.Widgets, contentDescription = null)
        }
    )
}
