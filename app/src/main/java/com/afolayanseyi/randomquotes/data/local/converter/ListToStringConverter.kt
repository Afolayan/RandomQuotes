package com.afolayanseyi.randomquotes.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import javax.inject.Inject

class ListToStringConverter {
    @TypeConverter
    fun listToJsonString(list: List<String>?) = Gson().toJson(list)

    @TypeConverter
    fun jsonStringToList(string: String?) =
        Gson().fromJson(string, Array<String>::class.java).toList()
}
