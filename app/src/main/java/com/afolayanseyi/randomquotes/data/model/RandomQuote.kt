package com.afolayanseyi.randomquotes.data.model

data class RandomQuote(
    val quoteId: String,
    val content: String,
    val author: String,
    val tags: List<String>,
    val dateAdded: String,
    val dateModified: String,
)
