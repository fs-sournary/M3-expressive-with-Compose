package com.itlifelang.m3expressivesample.ui.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AppBarWithSearch
import androidx.compose.material3.ExpandedFullScreenSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itlifelang.m3expressivesample.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithSearchScreen() {
    val textFieldState = rememberTextFieldState()
    val searchBarState = rememberSearchBarState()
    val scope = rememberCoroutineScope()
    val scrollBehavior = SearchBarDefaults.enterAlwaysSearchBarScrollBehavior()

    // --- FAKE SEARCH RESULTS FOR DEMONSTRATION ---
    // In a real app, 'searchResultsList' would come from your ViewModel or search logic,
    // and would update based on 'textFieldState.text'
    val currentSearchText = textFieldState.text.toString()
    val searchResultsList = remember(currentSearchText) {
        if (currentSearchText.isBlank()) {
            emptyList()
        } else {
            List(20) { "Result ${it + 1} for '$currentSearchText'" }
                .filter { it.contains(currentSearchText, ignoreCase = true) }
        }
    }

    val inputField =
        @Composable {
            SearchBarDefaults.InputField(
                modifier = Modifier.fillMaxWidth(),
                searchBarState = searchBarState,
                textFieldState = textFieldState,
                onSearch = { scope.launch { searchBarState.animateToCollapsed() } },
                placeholder = { Text("Search...") },
                leadingIcon = {
                    if (searchBarState.currentValue == SearchBarValue.Expanded) {
                        IconButton(
                            onClick = { scope.launch { searchBarState.animateToCollapsed() } }
                        ) {
                            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                        }
                    } else {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                },
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = null)
                        }

                        IconButton(onClick = {}) {
                            Image(
                                modifier = Modifier.size(30.dp).clip(CircleShape),
                                painter = painterResource(id = R.drawable.compose),
                                contentDescription = null,
                            )
                        }
                    }
                },
            )
        }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBarWithSearch(
                modifier = Modifier.padding(horizontal = 8.dp),
                scrollBehavior = scrollBehavior,
                state = searchBarState,
                inputField = inputField,
            )
            ExpandedFullScreenSearchBar(state = searchBarState, inputField = inputField) {
                SearchResults(
                    results = searchResultsList,
                    onResultClick = { result ->
                        textFieldState.setTextAndPlaceCursorAtEnd(result)
                        scope.launch { searchBarState.animateToCollapsed() }
                    }
                )
            }
        },
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val list = List(100) { "Text $it" }
            items(count = list.size) {
                Text(
                    text = list[it],
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                )
            }
        }
    }
}


/**
 * A custom composable to display search results.
 *
 * @param results The list of search result strings to display.
 * @param onResultClick Lambda called when a result item is clicked.
 */
@Composable
fun SearchResults(
    results: List<String>, // Or List<YourCustomResultObject>
    onResultClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (results.isEmpty()) {
        // Optional: Show a message if there are no results or if the search is empty
        Text(
            text = "No results found.",
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        return
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp), // Add some padding around the list
        verticalArrangement = Arrangement.spacedBy(4.dp) // Space between items
    ) {
        items(results) { resultItem ->
            Text(
                text = resultItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onResultClick(resultItem) }
                    .padding(vertical = 12.dp, horizontal = 16.dp) // Make items easier to tap
            )
        }
    }
}
