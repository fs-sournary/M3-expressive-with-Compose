package com.itlifelang.m3expressivesample.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AppBarRow
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedToggleButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedToggleButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.TonalToggleButton
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.itlifelang.m3expressivesample.util.actions

// See https://developer.android.com/develop/ui/compose/components/button
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonTypeScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "All button types",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    AppBarRow(
                        overflowIndicator = {
                            TooltipBox(
                                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                                    positioning = TooltipAnchorPosition.Below
                                ),
                                tooltip = { PlainTooltip { Text("Menu") } },
                                state = rememberTooltipState()
                            ) {
                                IconButton(onClick = { it.show() }) {
                                    Icon(
                                        imageVector = Icons.Filled.MoreVert,
                                        contentDescription = null
                                    )
                                }
                            }
                        },
                        maxItemCount = 4
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
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            // Filled button
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Filled Button",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "The filled button uses the basic Button composable. It is filled with a " +
                                "solid color by default."
                    )
                }
            }
            item {
                Button(onClick = {}) {
                    Text("Button")
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ToggleButtonExample()
                    Text("Toggle button", modifier = Modifier.padding(start = 8.dp))
                }
            }
            item {
                HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
            }

            // Filled tonal button
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Filled Tonal Button",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "The filled tonal button uses the FilledTonalButton composable. It" +
                                "is filled with a tonal color by default."
                    )
                }
            }
            item {
                FilledTonalButton(onClick = {}) {
                    Text("Button")
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TonalToggleButtonExample()
                    Text("Tonal toggle button", modifier = Modifier.padding(start = 8.dp))
                }
            }
            item {
                HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
            }

            // Outlined button
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Outlined Button",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "The outlined button uses the OutlinedButton composable. It appears" +
                                "with an outline by default."
                    )
                }
            }
            item {
                OutlinedButton(onClick = {}) {
                    Text("Button")
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedToggleButtonExample()
                    Text("Outlined toggle button", modifier = Modifier.padding(start = 8.dp))
                }
            }
            item {
                HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
            }

            // Elevated button
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Elevated Button",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "The elevated button used the ElevatedButton composable. It is a " +
                                "filled button with a shadow. It has a shadow that represents the " +
                                "elevated effect by default."
                    )
                }
            }
            item {
                ElevatedButton(onClick = {}) {
                    Text("Button")
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ElevatedToggleButtonExample()
                    Text("Elevated toggle button", modifier = Modifier.padding(start = 8.dp))
                }
            }
            item {
                HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
            }

            // Text button
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Text button",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "The text button uses the TextButton composable. It appears as only" +
                                "text until pressed. It does not have a fill or outline by default."
                    )
                }
            }
            item {
                TextButton(onClick = {}) {
                    Text("Button")
                }
            }
            item {
                HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
            }

            // Icon button
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Icon button",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text("All type of icon button")
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
                    }
                    Text(
                        text = "Icon button",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilledIconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
                    }
                    Text(
                        text = "Filled icon button",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilledTonalIconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
                    }
                    Text(
                        text = "Filled tonal icon button",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconToggleButtonExample()
                    Text(
                        "Icon toggle button",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FilledTonalIconToggleButtonExample()
                    Text(
                        "Filled tonal icon toggle button",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ToggleButtonExample(modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(false) }

    ToggleButton(
        modifier = modifier,
        checked = checked,
        onCheckedChange = { checked = it }
    ) {
        Text(text = if (checked) "Checked" else "Unchecked")
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun TonalToggleButtonExample(modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(false) }

    TonalToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = modifier
    ) {
        Text(text = if (checked) "Checked" else "Unchecked")
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun OutlinedToggleButtonExample(modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(false) }

    OutlinedToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = modifier
    ) {
        Text(text = if (checked) "Checked" else "Unchecked")
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ElevatedToggleButtonExample(modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(false) }

    ElevatedToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = modifier
    ) {
        Text(text = if (checked) "Checked" else "Unchecked")
    }
}

@Composable
fun IconToggleButtonExample(modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(false) }

    IconToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = modifier
    ) {
        if (checked) {
            Icon(imageVector = Icons.Filled.Check, contentDescription = null)
        } else {
            Icon(imageVector = Icons.Filled.Close, contentDescription = null)
        }
    }
}

@Composable
fun FilledTonalIconToggleButtonExample(modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(false) }

    FilledTonalIconToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = modifier
    ) {
        if (checked) {
            Icon(imageVector = Icons.Filled.Check, contentDescription = null)
        } else {
            Icon(imageVector = Icons.Filled.Close, contentDescription = null)
        }
    }
}
