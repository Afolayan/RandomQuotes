package com.afolayanseyi.randomquotes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.afolayanseyi.randomquotes.data.model.RandomQuote
import kotlin.random.Random

@Composable
internal fun Quote(quote: RandomQuote, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimensions.One),
        shape = RoundedCornerShape(Dimensions.One),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary.copy(
                red = Random.nextFloat(),
                blue = Random.nextFloat()
            )
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.Half),
        ) {
            Text(
                text = quote.content,
                modifier = Modifier.padding(top = Dimensions.One),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )
            Text(
                text = quote.author,
                modifier = Modifier.padding(Dimensions.One),
                style = MaterialTheme.typography.displaySmall
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = Dimensions.One)
            ) {
                Text(
                    text = quote.tags.joinToString(", "),
                    modifier = Modifier.padding(top = Dimensions.Half),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}
