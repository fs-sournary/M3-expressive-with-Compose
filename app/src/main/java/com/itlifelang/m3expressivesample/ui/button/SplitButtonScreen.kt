package com.itlifelang.m3expressivesample.ui.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AppBarRow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.itlifelang.m3expressivesample.util.actions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplitButtonScreen(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Split button", maxLines = 1, overflow = TextOverflow.Ellipsis)
                },
                actions = {
                    AppBarRow(
                        overflowIndicator = {
                            TooltipBox(
                                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                                    TooltipAnchorPosition.Below
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
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                scrollBehavior = scrollBehavior
            )
        },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            stickyHeader {
                Header(title = "Default split button")
            }
            item {
                DefaultSplitButton(modifier = Modifier.padding(horizontal = 16.dp))
            }

            stickyHeader {
                Header(title = "Split button with menu")
            }
            item {
                SplitButtonWithMenu(modifier = Modifier.padding(horizontal = 16.dp))
            }

            stickyHeader {
                Header("Tonal split button with menu")
            }
            item {
                TonalSplitButtonWithMenu(modifier = Modifier.padding(horizontal = 16.dp))
            }

            stickyHeader {
                Header("Elevated split button with menu")
            }
            item {
                ElevatedSplitButtonWithMenu(modifier = Modifier.padding(horizontal = 16.dp))
            }

            stickyHeader {
                Header("Outlined split button with menu")
            }
            item {
                OutlinedSplitButtonWithMenu(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@Composable
private fun Header(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        style = MaterialTheme.typography.titleMedium
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DefaultSplitButton(modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(false) }

    SplitButtonLayout(
        leadingButton = {
            SplitButtonDefaults.LeadingButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text("Edit")
            }
        },
        trailingButton = {
            TooltipBox(
                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                    TooltipAnchorPosition.Below
                ),
                tooltip = { PlainTooltip { Text("Menu") } },
                state = rememberTooltipState()
            ) {
                SplitButtonDefaults.TrailingButton(
                    checked = checked,
                    onCheckedChange = { checked = it }
                ) {
                    val rotation: Float by animateFloatAsState(
                        targetValue = if (checked) 180f else 0f,
                        label = "Trailing button rotation"
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        modifier = Modifier
                            .size(SplitButtonDefaults.TrailingIconSize)
                            .graphicsLayer {
                                rotationZ = rotation
                            },
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SplitButtonWithMenu(modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier) {
        SplitButtonLayout(
            leadingButton = {
                SplitButtonDefaults.LeadingButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Edit")
                }
            },
            trailingButton = {
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                        positioning = TooltipAnchorPosition.Below
                    ),
                    tooltip = { PlainTooltip { Text("Split button") } },
                    state = rememberTooltipState()
                ) {
                    val rotation by animateFloatAsState(
                        targetValue = if (checked) 180f else 0f,
                        label = "Trailing button rotation"
                    )
                    SplitButtonDefaults.TrailingButton(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            modifier = Modifier
                                .size(SplitButtonDefaults.TrailingIconSize)
                                .graphicsLayer {
                                    rotationZ = rotation
                                },
                            contentDescription = null
                        )
                    }
                }
            },
        )

        DropdownMenu(
            expanded = checked,
            onDismissRequest = { checked = false }
        ) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.Edit, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null
                    )
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text("Send feedback") },
                onClick = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = null) },
                trailingIcon = { Text("F11", textAlign = TextAlign.Center) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TonalSplitButtonWithMenu(modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier) {
        SplitButtonLayout(
            leadingButton = {
                SplitButtonDefaults.TonalLeadingButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Edit")
                }
            },
            trailingButton = {
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                        positioning = TooltipAnchorPosition.Below
                    ),
                    tooltip = { PlainTooltip { Text("Menu") } },
                    state = rememberTooltipState()
                ) {
                    SplitButtonDefaults.TonalTrailingButton(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    ) {
                        val rotation by animateFloatAsState(
                            targetValue = if (checked) 180f else 0f,
                            label = "Trailing button rotation"
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            modifier = Modifier
                                .size(SplitButtonDefaults.TrailingIconSize)
                                .graphicsLayer {
                                    rotationZ = rotation
                                },
                            contentDescription = null
                        )
                    }
                }
            }
        )

        DropdownMenu(expanded = checked, onDismissRequest = { checked = false }) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.Edit, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null
                    )
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text("Send feedback") },
                onClick = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = null) },
                trailingIcon = { Text("F11", textAlign = TextAlign.Center) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ElevatedSplitButtonWithMenu(modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier) {
        SplitButtonLayout(
            leadingButton = {
                SplitButtonDefaults.ElevatedLeadingButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Edit")
                }
            },
            trailingButton = {
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                        positioning = TooltipAnchorPosition.Below
                    ),
                    tooltip = { PlainTooltip { Text("Menu") } },
                    state = rememberTooltipState()
                ) {
                    SplitButtonDefaults.ElevatedTrailingButton(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    ) {
                        val rotation by animateFloatAsState(
                            targetValue = if (checked) 180f else 0f,
                            label = "Trailing button rotation"
                        )
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            modifier = Modifier
                                .size(SplitButtonDefaults.TrailingIconSize)
                                .graphicsLayer {
                                    rotationZ = rotation
                                },
                            contentDescription = null
                        )
                    }
                }
            }
        )

        DropdownMenu(expanded = checked, onDismissRequest = { checked = false }) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.Edit, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null
                    )
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text("Send feedback") },
                onClick = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = null) },
                trailingIcon = { Text("F11", textAlign = TextAlign.Center) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OutlinedSplitButtonWithMenu(modifier: Modifier = Modifier) {
    var checked: Boolean by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier) {
        SplitButtonLayout(
            leadingButton = {
                SplitButtonDefaults.OutlinedLeadingButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Edit")
                }
            },
            trailingButton = {
                TooltipBox(
                    positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                        positioning = TooltipAnchorPosition.Below
                    ),
                    tooltip = { PlainTooltip { Text("Menu") } },
                    state = rememberTooltipState()
                ) {
                    val rotation: Float by animateFloatAsState(
                        targetValue = if (checked) 180f else 0f,
                        label = "Trailing button rotation"
                    )
                    SplitButtonDefaults.OutlinedTrailingButton(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            modifier = Modifier
                                .size(SplitButtonDefaults.TrailingIconSize)
                                .graphicsLayer {
                                    rotationZ = rotation
                                },
                            contentDescription = null
                        )
                    }
                }
            }
        )

        DropdownMenu(expanded = checked, onDismissRequest = { checked = false }) {
            DropdownMenuItem(
                text = { Text("Edit")},
                onClick = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.Edit, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null
                    )
                }
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text("Send feedback") },
                onClick = {},
                leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = null) },
                trailingIcon = { Text("F11", textAlign = TextAlign.Center) }
            )
        }
    }
}
