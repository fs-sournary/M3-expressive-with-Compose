package com.itlifelang.m3expressivesample.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AppBarRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.itlifelang.m3expressivesample.navigation.AppNavKey
import com.itlifelang.m3expressivesample.navigation.ButtonType
import com.itlifelang.m3expressivesample.navigation.SplitButton
import com.itlifelang.m3expressivesample.navigation.LargeFlexibleAppBar
import com.itlifelang.m3expressivesample.navigation.MediumFlexibleAppBar
import com.itlifelang.m3expressivesample.navigation.SearchWithAppBar
import com.itlifelang.m3expressivesample.navigation.ButtonGroup
import com.itlifelang.m3expressivesample.navigation.ExtendedFabButton
import com.itlifelang.m3expressivesample.navigation.FabButton
import com.itlifelang.m3expressivesample.navigation.FabMenu
import com.itlifelang.m3expressivesample.navigation.LoadingIndicator
import com.itlifelang.m3expressivesample.navigation.NavigationBar
import com.itlifelang.m3expressivesample.navigation.ProgressIndicator
import com.itlifelang.m3expressivesample.navigation.SmallAppBar
import com.itlifelang.m3expressivesample.util.actions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateToDestination: (AppNavKey) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Material 3 Expressive",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                            TooltipAnchorPosition.Below
                        ),
                        tooltip = {
                            PlainTooltip { Text("Menu") }
                        },
                        state = rememberTooltipState()
                    ) {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                },
                actions = {
                    AppBarRow(
                        maxItemCount = 3,
                        overflowIndicator = {
                            TooltipBox(
                                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                                    TooltipAnchorPosition.Below
                                ),
                                tooltip = {
                                    PlainTooltip { Text("Overflow") }
                                },
                                state = rememberTooltipState()
                            ) {
                                IconButton(onClick = { it.show() }) {
                                    Icon(
                                        imageVector = Icons.Filled.MoreVert,
                                        contentDescription = "Overflow"
                                    )
                                }
                            }
                        }
                    ) {
                        actions.forEach { (key, value) ->
                            clickableItem(
                                onClick = {},
                                icon = {
                                    Icon(imageVector = value, contentDescription = key)
                                },
                                label = key
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            widgets.forEach { (key, values) ->
                stickyHeader {
                    Text(
                        text = key,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                items(values.size) {
                    val appNavKey = values[it]
                    Item(
                        title = appNavKey.name,
                        onClick = { navigateToDestination(appNavKey) }
                    )
                }
            }
        }
    }
}

private val widgets: Map<String, List<AppNavKey>> = mapOf(
    "App bar" to listOf(
        SearchWithAppBar("Search with App bar"),
        SmallAppBar("Small"),
        MediumFlexibleAppBar("Medium flexible"),
        LargeFlexibleAppBar("Large flexible")
    ),
    "Button" to listOf(
        ButtonType("Button type"),
        SplitButton("Split button"),
        ButtonGroup("Button group"),
        FabButton("Fab button"),
        ExtendedFabButton("Extended fab button"),
        FabMenu("Fab menu")
    ),
    "Indicator" to listOf(
        LoadingIndicator("Loading indicator"),
        ProgressIndicator("Progress indicator")
    ),
    "Navigation bar" to listOf(
        NavigationBar("Navigation bar")
    )
)
