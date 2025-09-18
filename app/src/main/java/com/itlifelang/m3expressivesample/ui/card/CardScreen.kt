package com.itlifelang.m3expressivesample.ui.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

/**
 * Example for Card composable in M3 Expressive.
 *
 * Check official documentation for more details:
 * - [M3 Expressive: Card](https://m3.material.io/components/cards/specs)
 * - [Card](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Card),
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Card") },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {
            stickyHeader {
                Text(
                    text = "Filled card",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                Card(
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(width = 240.dp, height = 180.dp),
                    // Change the container color
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = "This is a filled card",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            stickyHeader {
                Text(
                    text = "Outlined card",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                OutlinedCard(
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(width = 240.dp, height = 180.dp),
                    border = BorderStroke(width = 1.dp, color = Color.Black)
                ) {
                    Text(
                        text = "This is an outlined card",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            stickyHeader {
                Text(
                    text = "Elevated card",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                ElevatedCard (
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(width = 240.dp, height = 180.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 6.dp
                    )
                ) {
                    Text(
                        text = "This is an elevated card",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
