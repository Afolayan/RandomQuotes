package com.afolayanseyi.randomquotes.data.local

import com.afolayanseyi.randomquotes.data.model.RandomQuoteEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomQuoteLocalDataSource @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val randomQuotesDao: RandomQuotesDao,
) {
    suspend fun insertRandomQuote(randomQuoteEntity: RandomQuoteEntity) {
        withContext(coroutineDispatcher) {
            randomQuotesDao.insertRandomQuote(randomQuoteEntity)
        }
    }

    fun getRandomQuotes() = randomQuotesDao.getAllRandomQuotes()
}
