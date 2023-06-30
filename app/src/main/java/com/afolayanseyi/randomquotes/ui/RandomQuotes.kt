package com.afolayanseyi.randomquotes.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.afolayanseyi.randomquotes.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomQuotesScreen(
    modifier: Modifier = Modifier,
    viewModel: QuotesViewModel = hiltViewModel()
) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val uiState = viewModel.quotesUiState.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    val onErrorAction: (String) -> Unit = { message ->
        coroutineScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Long
            )
        }
    }

    val onFabClick = {
        viewModel.fetchRandomQuote()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        modifier = Modifier.padding(Dimensions.One),
                        style = MaterialTheme.typography.displayMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary
                        ),
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.new_quote)) },
                onClick = onFabClick,
                icon = { Icon(Icons.Filled.Add, stringResource(R.string.add_new_quote_button)) }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier
    ) { paddingValues ->
        RandomQuotesContent(
            uiState = uiState,
            onErrorAction = onErrorAction,
            paddingValues = paddingValues
        )
    }
}

@Composable
private fun RandomQuotesContent(
    uiState: RandomQuotesUiState,
    paddingValues: PaddingValues,
    onErrorAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = Dimensions.One),
        color = MaterialTheme.colorScheme.background
    ) {
        when (uiState) {
            is RandomQuotesUiState.Error -> {
                onErrorAction(
                    stringResource(id = uiState.errorMessageId)
                )
            }

            RandomQuotesUiState.Loading -> {
                Box {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(Dimensions.Five)
                            .align(
                                Alignment.Center
                            )
                    )
                }
            }

            is RandomQuotesUiState.Success -> {
                LazyColumn(
                    state = lazyListState,
                    contentPadding = PaddingValues(bottom = Dimensions.Two)
                ) {
                    items(uiState.quotes) { quote ->
                        Quote(quote)
                    }
                }
            }
        }
    }
}
