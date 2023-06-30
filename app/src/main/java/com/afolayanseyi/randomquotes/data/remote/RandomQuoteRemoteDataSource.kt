package com.afolayanseyi.randomquotes.data.remote

import com.afolayanseyi.randomquotes.data.model.RandomQuoteResponse
import okhttp3.ResponseBody
import java.io.IOException
import javax.inject.Inject

class RandomQuoteRemoteDataSource @Inject constructor(
    private val apiService: RandomQuoteApiService
){
    suspend fun fetchRandomQuote(): Result<RandomQuoteResponse> {
        val randomQuote = apiService.getRandomQuote()
        if (randomQuote.isSuccessful) {
            return (randomQuote.body()?.first()?.let { Result.success(it) }
                ?: Result.failure(IOException("No data received.")))
        }
        val error: ResponseBody? = randomQuote.errorBody()
        return Result.failure(IOException(error.toString()))
    }
}
