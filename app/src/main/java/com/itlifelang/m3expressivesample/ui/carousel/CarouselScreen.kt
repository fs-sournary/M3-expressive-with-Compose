package com.itlifelang.m3expressivesample.ui.carousel

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itlifelang.m3expressivesample.R

/**
 * Examples for Carousel. Using Pager to implement fullscreen carousel.
 *
 * Check official documentation for more details:
 * - [M3 Expressive: Carousel](https://m3.material.io/components/carousel/specs)
 * - [Carousel API reference](https://developer.android.com/reference/kotlin/androidx/compose/material3/carousel/package-summary#HorizontalMultiBrowseCarousel(androidx.compose.material3.carousel.CarouselState,androidx.compose.ui.unit.Dp,androidx.compose.ui.Modifier,androidx.compose.ui.unit.Dp,androidx.compose.foundation.gestures.TargetedFlingBehavior,androidx.compose.ui.unit.Dp,androidx.compose.ui.unit.Dp,androidx.compose.foundation.layout.PaddingValues,kotlin.Function2))
 * - [Carousel introduction](https://developer.android.com/develop/ui/compose/components/carousel)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text("Carousel")
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {
            stickyHeader {
                Text(
                    text = "Horizontal Multi Browse Carousel",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                MultiBrowseCarousel()
            }

            stickyHeader {
                Text(
                    text = "Horizontal Uncontained Carousel",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                UncontainedCarousel()
            }

            stickyHeader {
                Text(
                    text = "Horizontal Centered Hero Carousel",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                CenteredHeroCarousel()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MultiBrowseCarousel(modifier: Modifier = Modifier) {
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { items.count() },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp),
        preferredItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = items[i]
        Image(
            modifier = Modifier
                .height(205.dp)
                .maskClip(MaterialTheme.shapes.extraLarge),
            painter = painterResource(id = item.imageResId),
            contentDescription = item.contentDescription,
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UncontainedCarousel(modifier: Modifier = Modifier) {
    HorizontalUncontainedCarousel(
        state = rememberCarouselState { items.count() },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp),
        itemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = items[i]
        Image(
            modifier = Modifier
                .height(205.dp)
                .maskClip(MaterialTheme.shapes.extraLarge),
            painter = painterResource(id = item.imageResId),
            contentDescription = item.contentDescription,
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CenteredHeroCarousel(modifier: Modifier = Modifier) {
    HorizontalCenteredHeroCarousel(
        state = rememberCarouselState { items.count() },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp),
        maxItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = items[i]
        Image(
            modifier = Modifier
                .height(205.dp)
                .maskClip(MaterialTheme.shapes.extraLarge),
            painter = painterResource(id = item.imageResId),
            contentDescription = item.contentDescription,
            contentScale = ContentScale.Crop
        )
    }
}

private val items = listOf(
    CarouselItem(0, R.drawable.cupcake, "cupcake"),
    CarouselItem(1, R.drawable.donut, "donut"),
    CarouselItem(2, R.drawable.eclair, "eclair"),
    CarouselItem(3, R.drawable.froyo, "froyo"),
    CarouselItem(4, R.drawable.gingerbread, "gingerbread"),
)

data class CarouselItem(
    val id: Int,
    @DrawableRes val imageResId: Int,
    val contentDescription: String
)
