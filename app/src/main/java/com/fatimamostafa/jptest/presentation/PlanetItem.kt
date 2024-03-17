package com.fatimamostafa.jptest.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fatimamostafa.jptest.R
import com.fatimamostafa.jptest.domain.Planet
import com.fatimamostafa.jptest.ui.theme.AppTheme
import java.util.Random


@Composable
fun PlanetItem(
    planet: Planet,
    onPlanetClick: (Planet) -> Unit,
    modifier: Modifier = Modifier
) {
    val images = listOf(
        R.drawable.planet,
        R.drawable.moon,
        R.drawable.venus,
        R.drawable.earth
    )

    val random = Random()
    val selectedImage = images[random.nextInt(images.size)]
    Card(
        onClick = {
            onPlanetClick(planet)
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        modifier =
        Modifier
            .height(120.dp)
            .padding(horizontal = 12.dp)
            .padding(bottom = 26.dp),
    ) {
        Row(
            Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = selectedImage),
                contentDescription = null,
                modifier = Modifier.size(56.dp),
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = planet.name,
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium,
                modifier =
                Modifier
                    .padding(vertical = 16.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
            )
        }
    }
}

@Preview
@Composable
fun PlanetItemPreview() {
    AppTheme {
        PlanetItem(
            planet = Planet(
                name = "Planet",
                uid = "UID",
                url = "URL"
            ),
            modifier = Modifier.fillMaxWidth(),
            onPlanetClick = { // Define a mock implementation for preview
                println("Preview")
            }
        )
    }
}