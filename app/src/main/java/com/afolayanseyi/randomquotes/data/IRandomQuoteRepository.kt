package com.afolayanseyi.randomquotes.data

import com.afolayanseyi.randomquotes.data.model.RandomQuote
import kotlinx.coroutines.flow.Flow

interface IRandomQuoteRepository {
    fun getRandomQuotes(): Flow<List<RandomQuote>>

    fun fetchNewRandomQuote(): Flow<Result<RandomQuote>>
}
