package com.afolayanseyi.randomquotes.data.model

fun RandomQuoteEntity.toRandomQuote() = RandomQuote(
    quoteId = quoteId,
    content = content,
    author = author,
    tags = tags,
    dateAdded = dateAdded,
    dateModified = dateModified,
)

fun RandomQuoteResponse.toRandomQuoteEntity() = RandomQuoteEntity(
    quoteId = quoteId,
    content = content,
    author = author,
    tags = tags,
    dateAdded = dateAdded,
    dateModified = dateModified,
)

fun RandomQuoteResponse.toRandomQuote() = RandomQuote(
    quoteId = quoteId,
    content = content,
    author = author,
    tags = tags,
    dateAdded = dateAdded,
    dateModified = dateModified,
)
