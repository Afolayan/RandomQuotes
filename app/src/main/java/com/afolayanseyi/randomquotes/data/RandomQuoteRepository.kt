package com.afolayanseyi.randomquotes.data

import com.afolayanseyi.randomquotes.data.local.RandomQuoteLocalDataSource
import com.afolayanseyi.randomquotes.data.model.RandomQuote
import com.afolayanseyi.randomquotes.data.model.toRandomQuote
import com.afolayanseyi.randomquotes.data.model.toRandomQuoteEntity
import com.afolayanseyi.randomquotes.data.remote.RandomQuoteRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RandomQuoteRepository @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val localDataSource: RandomQuoteLocalDataSource,
    private val remoteDataSource: RandomQuoteRemoteDataSource
) : IRandomQuoteRepository {
    override fun getRandomQuotes() = localDataSource.getRandomQuotes()
        .map { localData -> localData.map { it.toRandomQuote() } }

    override fun fetchNewRandomQuote(): Flow<Result<RandomQuote>> = flow {
        try {
            val randomQuoteResponse = remoteDataSource.fetchRandomQuote()
            if (randomQuoteResponse.isSuccess) {
                val randomQuoteEntity = randomQuoteResponse.getOrThrow().toRandomQuoteEntity()
                localDataSource.insertRandomQuote(randomQuoteEntity)
                emit(randomQuoteResponse.map { it.toRandomQuote() })
            }
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }.flowOn(coroutineDispatcher)
}
