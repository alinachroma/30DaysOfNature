package com.example.a30daysofnature

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysofnature.data.NatureCardsRepository
import com.example.a30daysofnature.model.NatureCard
import com.example.a30daysofnature.ui.theme.ThirtyDaysOfNatureTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaysOfNatureTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Row {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium)
                    )
                )
            }
        }
    )
}

@Composable
fun NatureCard(
    natureCard: NatureCard,
    dayNumber: Int,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    ElevatedCard(
        elevation = CardDefaults.cardElevation(10.dp),
        shape = MaterialTheme.shapes.small,
        modifier = modifier.padding(
            start = dimensionResource(id = R.dimen.padding_medium),
            end = dimensionResource(id = R.dimen.padding_medium)
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            modifier = modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium),
                    end = dimensionResource(id = R.dimen.padding_medium)
                )
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
        ) {
            Text(
                text = stringResource(id = R.string.day) + " " + dayNumber,
                style = MaterialTheme.typography.headlineMedium,
                modifier = modifier.align(alignment = Alignment.End)
            )
            Box {
                Image(
                    painter = painterResource(id = natureCard.imageResourceId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                )
                Text(
                    text = stringResource(id = natureCard.date),
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.displayLarge,
                    modifier = modifier
                        .align(alignment = Alignment.TopEnd)
                        .offset(x = 1.dp, y = 1.dp)
                        .alpha(0.5f)
                )
                Text(
                    text = stringResource(id = natureCard.date),
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.White,
                    modifier = Modifier.align(alignment = Alignment.TopEnd)
                )
            }
            Text(
                text = stringResource(id = natureCard.quote)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = natureCard.quoteAuthor),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
                )
                Spacer(modifier = Modifier.weight(1f))
                LocationIcon(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded) {
                Location(
                    location = natureCard.location,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

@Composable
fun NatureCardsScreen(modifier: Modifier = Modifier) {
    val natureCards = NatureCardsRepository.listOfNatureCards
    Scaffold(
        topBar = { DaysOfNatureTopAppBar() },
        modifier = modifier
    ) {
        LazyColumn(
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        ) {
            itemsIndexed(natureCards) { index, item ->
                NatureCard(
                    natureCard = item,
                    dayNumber = index + 1,
                    modifier = modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_small),
                            end = dimensionResource(id = R.dimen.padding_small),
                            top = dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }
        }
    }
}

@Composable
fun Location(
    @StringRes location: Int,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = stringResource(id = R.string.where),
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = stringResource(id = location),
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}

@Composable
fun LocationIcon(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(id = R.string.expand),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NatureCardPreview() {
    ThirtyDaysOfNatureTheme {
        NatureCard(
            natureCard = NatureCard(
                imageResourceId = R.drawable.nature1,
                date = R.string.date_1,
                quote = R.string.quote_1,
                quoteAuthor = R.string.author_1,
                location = R.string.place_1,
                dayNumber = 1
            ),
            dayNumber = 1,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NatureCardDarkThemePreview() {
    ThirtyDaysOfNatureTheme(darkTheme = true) {
        NatureCard(
            natureCard = NatureCard(
                imageResourceId = R.drawable.nature1,
                date = R.string.date_1,
                quote = R.string.quote_1,
                quoteAuthor = R.string.author_1,
                location = R.string.place_1,
                dayNumber = 1
            ),
            dayNumber = 1,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NatureCardsListPreview() {
    ThirtyDaysOfNatureTheme {
        NatureCardsScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun NatureCardsListDarkThemePreview() {
    ThirtyDaysOfNatureTheme(darkTheme = true) {
        NatureCardsScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DaysOfNatureTopAppBarPreview() {
    ThirtyDaysOfNatureTheme {
        DaysOfNatureTopAppBar()
    }
}

@Preview(showBackground = true)
@Composable
fun LocationPreview() {
    ThirtyDaysOfNatureTheme {
        Location(
            location = R.string.place_1,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LocationDarkThemePreview() {
    ThirtyDaysOfNatureTheme(darkTheme = true) {
        Location(
            location = R.string.place_1,
            modifier = Modifier
        )
    }
}

