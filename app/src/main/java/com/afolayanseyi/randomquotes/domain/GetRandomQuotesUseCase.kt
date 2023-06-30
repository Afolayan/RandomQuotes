package com.afolayanseyi.randomquotes.domain

import com.afolayanseyi.randomquotes.data.RandomQuoteRepository
import javax.inject.Inject

class GetRandomQuotesUseCase @Inject constructor(
    private val repository: RandomQuoteRepository
) {
    operator fun invoke() = repository.getRandomQuotes()
}
