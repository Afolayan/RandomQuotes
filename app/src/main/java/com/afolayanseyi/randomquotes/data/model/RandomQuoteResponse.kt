package com.afolayanseyi.randomquotes.data.model

import com.google.gson.annotations.SerializedName

data class RandomQuoteResponse(
    @SerializedName("_id") val quoteId: String,
    @SerializedName("content") val content: String,
    @SerializedName("author") val author: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("dateAdded") val dateAdded: String,
    @SerializedName("dateModified") val dateModified: String,
)
