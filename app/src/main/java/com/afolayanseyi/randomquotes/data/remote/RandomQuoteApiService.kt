package com.afolayanseyi.randomquotes.data.remote

import com.afolayanseyi.randomquotes.data.model.RandomQuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

private const val RandomQuotePath = "quotes/random"

interface RandomQuoteApiService {

    @Headers("Accept: application/json")
    @GET(RandomQuotePath)
    suspend fun getRandomQuote(): Response<List<RandomQuoteResponse>>
}
