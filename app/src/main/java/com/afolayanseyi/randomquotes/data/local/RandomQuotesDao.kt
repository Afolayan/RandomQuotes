package com.afolayanseyi.randomquotes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afolayanseyi.randomquotes.data.model.RandomQuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RandomQuotesDao {

    @Query("SELECT * FROM $RandomQuotesTable")
    fun getAllRandomQuotes(): Flow<List<RandomQuoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRandomQuote(randomQuote: RandomQuoteEntity)
}
