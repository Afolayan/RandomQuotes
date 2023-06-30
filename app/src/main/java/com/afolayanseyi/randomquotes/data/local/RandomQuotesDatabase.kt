package com.afolayanseyi.randomquotes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.afolayanseyi.randomquotes.data.local.converter.DateConverter
import com.afolayanseyi.randomquotes.data.local.converter.ListToStringConverter
import com.afolayanseyi.randomquotes.data.model.RandomQuoteEntity

const val RandomQuotesDb = "random_quotes_db"
const val RandomQuotesTable = "random_quotes"

@Database(
    entities = [RandomQuoteEntity::class],
    version = 1
)
@TypeConverters(DateConverter::class, ListToStringConverter::class)
abstract class RandomQuotesDatabase : RoomDatabase() {
    abstract fun randomQuotesDao(): RandomQuotesDao
}
