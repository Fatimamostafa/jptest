package com.fatimamostafa.jptest.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.fatimamostafa.jptest.domain.Planet


@Composable
fun PlanetScreen(
    planets: LazyPagingItems<Planet>,
    modifier: Modifier = Modifier,
    onPlanetClick: (Planet) -> Unit = {},
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = planets.loadState) {
        if (planets.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (planets.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        if (planets.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    horizontal = 12.dp,
                    vertical = 24.dp,
                ),
            ) {
                items(
                    count = planets.itemCount,
                    key = planets.itemKey { it.uid },
                ) { index ->
                    val item = planets[index]
                    if (item != null) {
                        PlanetItem(
                            onPlanetClick = onPlanetClick,
                            planet = item,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                }
            }
        }
    }

}