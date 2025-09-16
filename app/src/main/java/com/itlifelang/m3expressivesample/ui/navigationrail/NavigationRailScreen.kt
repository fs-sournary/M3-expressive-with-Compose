package com.itlifelang.m3expressivesample.ui.navigationrail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationRailScreen() {
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

    Row(modifier = Modifier.fillMaxSize()) {
        NavigationRail(
            modifier = Modifier.fillMaxHeight(), // Take full height
            header = { // Optional header (e.g., a FAB or logo)
                FloatingActionButton(
                    onClick = { /* TODO: Action for FAB in header */ },
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                ) {
                    Icon(Icons.Filled.Edit, contentDescription = "App Icon")
                }
            }
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
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
                    label = { Text(item) },
                    // alwaysShowLabel = false // Show label only when selected, or always if true
                )
            }
        }

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text("Navigation Rail") },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues
            ) {
                items(100) {
                    Item(index = it)
                }
            }
        }
    }
}

@Composable
private fun Item(modifier: Modifier = Modifier, index: Int) {
    val leadingBackground by rememberSaveable(stateSaver = saver) {
        mutableStateOf(getRandomColor())
    }

    ListItem(
        headlineContent = {
            Text("Item $index")
        },
        modifier = modifier,
        supportingContent = {
            Text("This is a supporting text for item $index")
        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .requiredSize(32.dp)
                    .background(color = leadingBackground, shape = CircleShape)
            ) {
                Text(
                    text = "$index",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    )
}

private fun getRandomColor(): Color {
    val random = Random.Default
    val red = random.nextInt()
    val green = random.nextInt()
    val blue = random.nextInt()
    return Color(red, green, blue)
}

val colorSaver = listSaver<Color, Any>(
    save = { listOf(it.red, it.green, it.blue) },
    restore = { Color(it[0] as Float, it[1] as Float, it[2] as Float) }
)

val saver = run {
    val redKey = "red"
    val greenKey = "green"
    val blueKey = "blue"
    mapSaver(
        save = { mapOf(redKey to it.red, greenKey to it.green, blueKey to it.blue) },
        restore = { Color(it[redKey] as Float, it[greenKey] as Float, it[blueKey] as Float) }
    )
}
