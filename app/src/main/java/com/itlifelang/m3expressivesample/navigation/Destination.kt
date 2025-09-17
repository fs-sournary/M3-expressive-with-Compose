package com.itlifelang.m3expressivesample.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey

@Serializable
open class AppNavKey(val name: String): NavKey

// App bar
@Serializable
data class SearchWithAppBar(val title: String) : AppNavKey(title)

// Badge
@Serializable
data class Badge(val title: String): AppNavKey(title)

// Card
@Serializable
data class Card(val title: String): AppNavKey(title)

@Serializable
data class SmallAppBar(val title: String): AppNavKey(title)

@Serializable
data class MediumFlexibleAppBar(val title: String): AppNavKey(title)

@Serializable
data class LargeFlexibleAppBar(val title: String): AppNavKey(title)

// Button
@Serializable
data class ButtonType(val title: String) : AppNavKey(title)

@Serializable
data class SplitButton(val title: String): AppNavKey(title)

@Serializable
data class ButtonGroup(val title: String): AppNavKey(title)

@Serializable
data class FabButton(val title: String): AppNavKey(title)

@Serializable
data class ExtendedFabButton(val title: String): AppNavKey(title)

@Serializable
data class FabMenu(val title: String): AppNavKey(title)

// Indicator
@Serializable
data class LoadingIndicator(val title: String): AppNavKey(title)

@Serializable
data class ProgressIndicator(val title: String): AppNavKey(title)

// Navigation bar
@Serializable
data class NavigationBar(val title: String): AppNavKey(title)

// Navigation rail
@Serializable
data class NavigationRail(val title: String): AppNavKey(title)

// Slider
@Serializable
data class Slider(val title: String): AppNavKey(title)
