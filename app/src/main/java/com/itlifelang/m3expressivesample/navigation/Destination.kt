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

data class ButtonGroup(val title: String): AppNavKey(title)

data class FabButton(val title: String): AppNavKey(title)

data class ExtendedFabButton(val title: String): AppNavKey(title)

data class FabMenu(val title: String): AppNavKey(title)
