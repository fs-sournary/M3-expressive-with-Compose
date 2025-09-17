package com.itlifelang.m3expressivesample.ui.badge

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

/**
 * Example for Badge composable in M3 Expressive.
 *
 * Check official documentation for more details: [M3 Expressive: Badge](https://m3.material.io/components/badges/specs),
 * [Badge](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#Badge),
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgeScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Badge") },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            item {
                Text(text = "Small Badge", style = MaterialTheme.typography.titleMedium)
            }
            item {
                BadgedBox(
                    badge = {
                        Badge()
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Mail, contentDescription = null)
                }
            }

            item {
                Text(text = "Large Badge: 1 text", style = MaterialTheme.typography.titleMedium)
            }
            item {
                BadgedBox(
                    badge = {
                        Badge {
                            Text("3")
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Mail, contentDescription = null)
                }
            }

            item {
                Text(
                    text = "Large Badge should only display 4 characters",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                BadgedBox(
                    badge = {
                        Badge {
                            Text("999+")
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Mail, contentDescription = null)
                }
            }
        }
    }
}
