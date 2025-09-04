package com.itlifelang.m3expressivesample.ui.button

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Snooze
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FabMenuScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val listState = rememberLazyListState()
    val fabVisible by remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }
    var fabExpanded by rememberSaveable { mutableStateOf(false) }

    BackHandler(fabExpanded) { fabExpanded = false }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text("Fab Menu")
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FabMenuExample(
                modifier = Modifier.offset(x = 16.dp, y = 16.dp),
                visible = fabVisible,
                expanded = fabExpanded,
                onCheckedChange = { fabExpanded = it },
                onItemClick = { fabExpanded = false }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            state = listState
        ) {
            for (index in 0 until 100) {
                item {
                    Text(
                        text = "Item $index",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    }

//    Box {
//        LazyColumn(
//            state = listState,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            for (index in 0 until 100) {
//                item {
//                    Text(
//                        text = "Item $index",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp)
//                    )
//                }
//            }
//        }
//
//        BackHandler(fabExpanded) { fabExpanded = false }
//
//        FabMenuExample(
//            modifier = Modifier.align(Alignment.BottomEnd),
//            visible = fabVisible,
//            expanded = fabExpanded,
//            onCheckedChange = { fabExpanded = it },
//            onItemClick = { fabExpanded = false }
//        )
//    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun FabMenuExample(
    modifier: Modifier = Modifier,
    visible: Boolean,
    expanded: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onItemClick: () -> Unit
) {
    val items = listOf(
        Icons.AutoMirrored.Filled.Message to "Reply",
        Icons.Filled.People to "Reply all",
        Icons.Filled.Contacts to "Forward",
        Icons.Filled.Snooze to "Snooze",
        Icons.Filled.Archive to "Archive",
        Icons.AutoMirrored.Filled.Label to "Label",
    )

    // Default padding horizontal = 16.dp, bottom = 16.dp
    FloatingActionButtonMenu(
        expanded = expanded,
        modifier = modifier,
        button = {
            ToggleFloatingActionButton(
                checked = expanded,
                onCheckedChange = onCheckedChange,
                modifier = Modifier
                    .semantics {
                        traversalIndex = -1f
                        stateDescription = if (expanded) "Expanded" else "Collapse"
                        contentDescription = "Toggle Menu"
                    }
                    .animateFloatingActionButton(
                        visible = visible || expanded,
                        alignment = Alignment.BottomEnd
                    )
            ) {
                val imageVector by remember {
                    derivedStateOf {
                        if (checkedProgress > 0.5f) Icons.Filled.Close else Icons.Filled.Add
                    }
                }
                Icon(
                    painter = rememberVectorPainter(image = imageVector),
                    contentDescription = null,
                    modifier = Modifier.animateIcon(checkedProgress = { checkedProgress })
                )
            }
        }
    ) {
        items.forEachIndexed { i, item ->
            FloatingActionButtonMenuItem(
                modifier = Modifier.semantics {
                    isTraversalGroup = true
                    // Add a custom a11y action to allow closing the menu when focusing
                    // the last menu item, since the close button comes before the first
                    // menu item in the traversal order.
                    if (i == items.size - 1) {
                        customActions = listOf(
                            CustomAccessibilityAction(
                                label = "Close menu",
                                action = {
                                    onItemClick()
                                    true
                                },
                            )
                        )
                    }
                },
                onClick = onItemClick,
                text = { Text(item.second) },
                icon = { Icon(item.first, contentDescription = null) }
            )
        }
    }
}
