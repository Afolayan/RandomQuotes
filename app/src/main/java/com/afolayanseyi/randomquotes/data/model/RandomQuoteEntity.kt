package com.afolayanseyi.randomquotes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afolayanseyi.randomquotes.data.local.RandomQuotesTable

@Entity(tableName = RandomQuotesTable)
data class RandomQuoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val quoteId: String,
    val content: String,
    val author: String,
    val tags: List<String>,
    val dateAdded: String,
    val dateModified: String,
)
