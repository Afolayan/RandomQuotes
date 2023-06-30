package com.afolayanseyi.randomquotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.afolayanseyi.randomquotes.ui.RandomQuotesScreen
import com.afolayanseyi.randomquotes.ui.theme.RandomQuotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomQuotesTheme {
                RandomQuotesScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RandomQuotesTheme {
        RandomQuotesScreen()
    }
}
