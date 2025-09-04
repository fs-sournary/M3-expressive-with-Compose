package com.itlifelang.m3expressivesample

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.itlifelang.m3expressivesample.navigation.ButtonGroup
import com.itlifelang.m3expressivesample.navigation.ButtonType
import com.itlifelang.m3expressivesample.navigation.ExtendedFabButton
import com.itlifelang.m3expressivesample.navigation.FabButton
import com.itlifelang.m3expressivesample.navigation.FabMenu
import com.itlifelang.m3expressivesample.navigation.Home
import com.itlifelang.m3expressivesample.navigation.LargeFlexibleAppBar
import com.itlifelang.m3expressivesample.navigation.MediumFlexibleAppBar
import com.itlifelang.m3expressivesample.navigation.SearchWithAppBar
import com.itlifelang.m3expressivesample.navigation.SplitButton
import com.itlifelang.m3expressivesample.ui.appbar.AppBarWithSearchScreen
import com.itlifelang.m3expressivesample.ui.appbar.LargeFlexibleTopAppBarScreen
import com.itlifelang.m3expressivesample.ui.appbar.MediumFlexibleTopAppBarScreen
import com.itlifelang.m3expressivesample.ui.button.ButtonGroupScreen
import com.itlifelang.m3expressivesample.ui.button.ButtonTypeScreen
import com.itlifelang.m3expressivesample.ui.button.ExtendedFabButtonScreen
import com.itlifelang.m3expressivesample.ui.button.FabButtonScreen
import com.itlifelang.m3expressivesample.ui.button.FabMenuScreen
import com.itlifelang.m3expressivesample.ui.button.SplitButtonScreen
import com.itlifelang.m3expressivesample.ui.home.HomeScreen
import com.itlifelang.m3expressivesample.ui.theme.M3ExpressiveSampleTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            window.isNavigationBarContrastEnforced = false
        }

        setContent {
            M3ExpressiveSampleTheme {
                val backStack = rememberNavBackStack(Home)

                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = entryProvider {
                        entry<Home>(
                            metadata = NavDisplay.transitionSpec {
                                slideInVertically(
                                    initialOffsetY = { it },
                                    animationSpec = tween(1000)
                                ) togetherWith ExitTransition.KeepUntilTransitionsFinished
                            } + NavDisplay.popTransitionSpec {
                                EnterTransition.None togetherWith slideOutVertically(
                                    targetOffsetY = { it },
                                    animationSpec = tween(1000)
                                )
                            }
                        ) {
                            HomeScreen { backStack.add(it) }
                        }

                        // App Bar
                        entry<SearchWithAppBar> {
                            AppBarWithSearchScreen()
                        }
                        entry<MediumFlexibleAppBar> {
                            MediumFlexibleTopAppBarScreen()
                        }
                        entry<LargeFlexibleAppBar> {
                            LargeFlexibleTopAppBarScreen()
                        }

                        // Button
                        entry<ButtonType> {
                            ButtonTypeScreen()
                        }
                        entry<SplitButton> {
                            SplitButtonScreen()
                        }
                        entry<ButtonGroup> {
                            ButtonGroupScreen()
                        }
                        entry<FabButton> {
                            FabButtonScreen()
                        }
                        entry<ExtendedFabButton> {
                            ExtendedFabButtonScreen()
                        }
                        entry<FabMenu> {
                            FabMenuScreen()
                        }
                    },
                    transitionSpec = {
                        // Slide in from the right
                        slideInHorizontally(initialOffsetX = { it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { -it })
                    },
                    popTransitionSpec = {
                        // Previous content slides in from the left, current content slides out from the left
                        slideInHorizontally(initialOffsetX = { -it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { it })
                    },
                    predictivePopTransitionSpec = {
                        // Previous content slides in from the left, current content slides out from the left
                        slideInHorizontally(initialOffsetX = { -it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { it })
                    }
                )
            }
        }
    }
}
