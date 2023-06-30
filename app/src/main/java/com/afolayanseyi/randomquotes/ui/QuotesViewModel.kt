package com.afolayanseyi.randomquotes.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afolayanseyi.randomquotes.R
import com.afolayanseyi.randomquotes.data.model.RandomQuote
import com.afolayanseyi.randomquotes.domain.FetchRandomQuoteUseCase
import com.afolayanseyi.randomquotes.domain.GetRandomQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val fetchRandomQuoteUseCase: FetchRandomQuoteUseCase,
    private val getRandomQuotesUseCase: GetRandomQuotesUseCase
) : ViewModel() {

    private val _quotesUiState: MutableStateFlow<RandomQuotesUiState> =
        MutableStateFlow(RandomQuotesUiState.Loading)
    val quotesUiState: StateFlow<RandomQuotesUiState> get() = _quotesUiState.asStateFlow()

    init {
        getRandomQuotes()
    }

    private fun getRandomQuotes() {
        getRandomQuotesUseCase()
            .onEach { quotes: List<RandomQuote> ->
                if (quotes.isNotEmpty()) {
                    _quotesUiState.update {
                        RandomQuotesUiState.Success(quotes = quotes)
                    }
                } else {
                    fetchRandomQuote()
                }
            }
            .launchIn(viewModelScope)
    }

    fun fetchRandomQuote() {
        viewModelScope.launch {
            fetchRandomQuoteUseCase().collectLatest { value: Result<RandomQuote> ->
                value.onFailure { error ->
                    val errorMessageId = when (error) {
                        is UnknownHostException -> R.string.offline_error_fetching_new_quote
                        else -> R.string.error_fetching_new_quote
                    }
                    _quotesUiState.update {
                        RandomQuotesUiState.Error(errorMessageId = errorMessageId)
                    }
                }
            }
        }
    }
}

sealed class RandomQuotesUiState {
    data class Success(val quotes: List<RandomQuote>) : RandomQuotesUiState()
    data class Error(@StringRes val errorMessageId: Int) : RandomQuotesUiState()
    object Loading : RandomQuotesUiState()
}
