package com.itlifelang.m3expressivesample.ui.slider

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Slider") },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {
            stickyHeader {
                Text(
                    text = "Standard slider",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                StandardSlider()
            }

            stickyHeader {
                Text(
                    text = "Stepped standard slider",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                SteppedStandardSlider()
            }

            stickyHeader {
                Text(
                    text = "Custom thumb slider",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                CustomThumbSlider()
            }

            stickyHeader {
                Text(
                    text = "Custom thumb and track slider",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                CustomThumbTrackSlider()
            }

            stickyHeader {
                Text(
                    text = "Custom track slider",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                CustomTrackSlider()
            }
        }
    }
}

@Composable
private fun StandardSlider(modifier: Modifier = Modifier) {
    // Use rememberSaveable to persist the slider position in the list
    var sliderPosition by rememberSaveable { mutableFloatStateOf(0f) }

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(text = "%.2f".format(sliderPosition))
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SteppedStandardSlider(modifier: Modifier = Modifier) {
    val sliderState = rememberSliderState(
        // The number of step between 0 and 100: 10, 20, 30...90
        steps = 9,
        valueRange = 0f..100f,
        onValueChangeFinished = {}
    )

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(text = "%.2f".format(sliderState.value))
        Slider(state = sliderState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomThumbSlider(modifier: Modifier = Modifier) {
    var sliderPosition by rememberSaveable {
        mutableFloatStateOf(0f)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            interactionSource = interactionSource,
            onValueChangeFinished = {},
            thumb = {
                Label(
                    label = {
                        PlainTooltip(
                            modifier = Modifier
                                .sizeIn(45.dp, 25.dp)
                                .wrapContentWidth()
                        ) {
                            Text("%.2f".format(sliderPosition))
                        }
                    },
                    interactionSource = interactionSource
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        tint = Color.Red
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomThumbTrackSlider(modifier: Modifier = Modifier) {
    val sliderState = rememberSliderState(
        valueRange = 0f..100f,
        onValueChangeFinished = {
            // launch some business logic update with the state you hold
            // viewModel.updateSelectedSliderValue(sliderPosition)
        },
    )
    val interactionSource = remember { MutableInteractionSource() }
    val colors = SliderDefaults.colors(thumbColor = Color.Red, activeTrackColor = Color.Blue)

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(text = "%.2f".format(sliderState.value))
        Slider(
            state = sliderState,
            interactionSource = interactionSource,
            thumb = {
                SliderDefaults.Thumb(interactionSource = interactionSource, colors = colors)
            },
            track = { SliderDefaults.Track(colors = colors, sliderState = sliderState) },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun CustomTrackSlider(modifier: Modifier = Modifier) {
    val sliderState = rememberSliderState(
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
        )
    val interactionSource = remember { MutableInteractionSource() }
    val startIcon = rememberVectorPainter(Icons.Filled.MusicNote)
    val endIcon = rememberVectorPainter(Icons.Filled.MusicOff)
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(text = "%.2f".format(sliderState.value))
        Slider(
            state = sliderState,
            interactionSource = interactionSource,
            track = {
                val iconSize = DpSize(20.dp, 20.dp)
                val iconPadding = 10.dp
                val thumbTrackGapSize = 6.dp
                val activeIconColor = SliderDefaults.colors().activeTickColor
                val inactiveIconColor = SliderDefaults.colors().inactiveTickColor
                val trackIconStart: DrawScope.(Offset, Color) -> Unit = { offset, color ->
                    translate(offset.x + iconPadding.toPx(), offset.y) {
                        with(startIcon) {
                            draw(iconSize.toSize(), colorFilter = ColorFilter.tint(color))
                        }
                    }
                }
                val trackIconEnd: DrawScope.(Offset, Color) -> Unit = { offset, color ->
                    translate(offset.x - iconPadding.toPx() - iconSize.toSize().width, offset.y) {
                        with(endIcon) {
                            draw(iconSize.toSize(), colorFilter = ColorFilter.tint(color))
                        }
                    }
                }
                SliderDefaults.Track(
                    sliderState = sliderState,
                    modifier =
                        Modifier.height(36.dp).drawWithContent {
                            drawContent()

                            val yOffset = size.height / 2 - iconSize.toSize().height / 2
                            val activeTrackStart = 0f
                            val activeTrackEnd =
                                size.width * sliderState.coercedValueAsFraction -
                                        thumbTrackGapSize.toPx()
                            val inactiveTrackStart = activeTrackEnd + thumbTrackGapSize.toPx() * 2
                            val inactiveTrackEnd = size.width

                            val activeTrackWidth = activeTrackEnd - activeTrackStart
                            val inactiveTrackWidth = inactiveTrackEnd - inactiveTrackStart
                            if (
                                iconSize.toSize().width < activeTrackWidth - iconPadding.toPx() * 2
                            ) {
                                trackIconStart(Offset(activeTrackStart, yOffset), activeIconColor)
                                trackIconEnd(Offset(activeTrackEnd, yOffset), activeIconColor)
                            }
                            if (
                                iconSize.toSize().width <
                                inactiveTrackWidth - iconPadding.toPx() * 2
                            ) {
                                trackIconStart(
                                    Offset(inactiveTrackStart, yOffset),
                                    inactiveIconColor,
                                )
                                trackIconEnd(Offset(inactiveTrackEnd, yOffset), inactiveIconColor)
                            }
                        },
                    trackCornerSize = 12.dp,
                    drawStopIndicator = null,
                    thumbTrackGapSize = thumbTrackGapSize,
                )
            },
        )
    }
}
