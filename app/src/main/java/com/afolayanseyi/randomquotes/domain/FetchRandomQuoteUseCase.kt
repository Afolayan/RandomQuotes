package com.afolayanseyi.randomquotes.domain

import com.afolayanseyi.randomquotes.data.RandomQuoteRepository
import javax.inject.Inject

class FetchRandomQuoteUseCase @Inject constructor(
    private val repository: RandomQuoteRepository
) {
    operator fun invoke() = repository.fetchNewRandomQuote()
}
