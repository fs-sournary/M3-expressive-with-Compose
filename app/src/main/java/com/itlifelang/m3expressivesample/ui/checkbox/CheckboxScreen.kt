package com.itlifelang.m3expressivesample.ui.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import kotlin.math.floor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Checkbox") },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {
            stickyHeader {
                Text(
                    text = "Simple checkbox",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                SimpleCheckbox {}
            }

            stickyHeader {
                Text(
                    text = "Custom checkbox",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                CustomCheckbox()
            }

            stickyHeader {
                Text(
                    text = "Standard tri-state checkbox",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                StandardTriStateCheckbox()
            }
        }
    }
}

@Composable
private fun SimpleCheckbox(modifier: Modifier = Modifier, onValueChange: (Boolean) -> Unit) {
    var checked by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .toggleable(
                value = checked,
                onValueChange = {
                    checked = it
                    onValueChange(it)
                },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = null
        )
        Text(
            text = "Checkbox",
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
private fun CustomCheckbox() {
    val strokeWidthPx = with(LocalDensity.current) {
        floor(CheckboxDefaults.StrokeWidth.toPx())
    }
    val checkMarkStroke = remember(strokeWidthPx) {
        Stroke(width = strokeWidthPx, cap = StrokeCap.Round, join = StrokeJoin.Round)
    }
    val outlineStroke = remember(strokeWidthPx) {
        Stroke(width = strokeWidthPx)
    }

    var checked by rememberSaveable {
        mutableStateOf(false)
    }
    Checkbox(
        checked = checked,
        onCheckedChange = { checked = it },
        checkmarkStroke = checkMarkStroke,
        outlineStroke = outlineStroke
    )
}

@Composable
private fun StandardTriStateCheckbox(modifier: Modifier = Modifier) {
    var dailyChecked by rememberSaveable {
        mutableStateOf(true)
    }
    var weeklyChecked by rememberSaveable {
        mutableStateOf(true)
    }
    val parentChecked: ToggleableState = remember(dailyChecked, weeklyChecked) {
        when {
            dailyChecked && weeklyChecked -> ToggleableState.On
            !dailyChecked && !weeklyChecked -> ToggleableState.Off
            else -> ToggleableState.Indeterminate
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .triStateToggleable(
                    state = parentChecked,
                    onClick = {
                        val newState = parentChecked != ToggleableState.On
                        dailyChecked = newState
                        weeklyChecked = newState
                    },
                    role = Role.Checkbox
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TriStateCheckbox(state = parentChecked, onClick = null)
            Text("Receive email")
        }

        Column(
            modifier = Modifier.padding(start = 24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .toggleable(
                        value = dailyChecked,
                        onValueChange = { dailyChecked = it },
                        role = Role.Checkbox
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = dailyChecked, onCheckedChange = null)
                Text(text = "Daily")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .toggleable(
                        value = weeklyChecked,
                        onValueChange = { weeklyChecked = it },
                        role = Role.Checkbox
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = weeklyChecked, onCheckedChange = null)
                Text(text = "Weekly")
            }
        }
    }
}
