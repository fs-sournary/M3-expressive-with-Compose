package com.itlifelang.m3expressivesample.ui.navigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarArrangement
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun NavigationBarScreen(
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists", "Profile", "Setting")
    val selectedIcons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Favorite,
        Icons.Filled.Star,
        Icons.Filled.Person2,
        Icons.Filled.Settings
    )
    val unselectedIcons = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.FavoriteBorder,
        Icons.Outlined.StarBorder,
        Icons.Outlined.Person2,
        Icons.Outlined.Settings
    )

    val listState = rememberLazyListState()
    val fabVisible by remember {
        derivedStateOf { listState.firstVisibleItemIndex != 0 }
    }

    val isMediumSize = remember(windowSizeClass) {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Navigation Bar") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            ShortNavigationBar(
                arrangement = if (!isMediumSize) {
                    ShortNavigationBarArrangement.EqualWeight
                } else {
                    ShortNavigationBarArrangement.Centered
                }
            ) {
                items.forEachIndexed { index, item ->
                    ShortNavigationBarItem(
                        iconPosition = if (!isMediumSize) {
                            NavigationItemIconPosition.Top
                        } else {
                            NavigationItemIconPosition.Start
                        },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = {
                            // Add a badge to each icon
                            when (index) {
                                0 -> BadgedBox(
                                    badge = {
                                        Badge(
                                            modifier = Modifier.semantics {
                                                contentDescription = "New notification"
                                            }
                                        )
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (selectedItem == index) {
                                            selectedIcons[index]
                                        } else {
                                            unselectedIcons[index]
                                        },
                                        contentDescription = null
                                    )
                                }

                                1 -> BadgedBox(
                                    badge = {
                                        Badge(
                                            modifier = Modifier.semantics {
                                                contentDescription = "New notification"
                                            }
                                        ) {
                                            Text("1")
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (selectedItem == index) {
                                            selectedIcons[index]
                                        } else {
                                            unselectedIcons[index]
                                        },
                                        contentDescription = null
                                    )
                                }

                                2 -> BadgedBox(
                                    badge = {
                                        Badge(
                                            modifier = Modifier.semantics {
                                                contentDescription = "New notification"
                                            }
                                        ) {
                                            Text("99")
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (selectedItem == index) {
                                            selectedIcons[index]
                                        } else {
                                            unselectedIcons[index]
                                        },
                                        contentDescription = null
                                    )
                                }

                                else -> Icon(
                                    imageVector = if (selectedItem == index) {
                                        selectedIcons[index]
                                    } else {
                                        unselectedIcons[index]
                                    },
                                    contentDescription = null
                                )
                            }
                        },
                        label = { Text(item) }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                modifier = Modifier.animateFloatingActionButton(
                    visible = fabVisible,
                    alignment = Alignment.BottomEnd
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowUpward,
                    contentDescription = "Scroll to top"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            state = listState
        ) {
            stickyHeader {
                Text(
                    text = "Scroll to see the fab button",
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                )
            }

            items(count = 100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 600)
@Composable
fun NavigationBarScreenCompactPreview() {
    NavigationBarScreen()
}

@Preview(showBackground = true, widthDp = 1200, heightDp = 600)
@Composable
fun NavigationBarScreenMediumPreview() {
    NavigationBarScreen()
}
