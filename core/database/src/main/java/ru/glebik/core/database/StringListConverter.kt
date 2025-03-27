package ru.glebik.core.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class StringListConverter(
    private val gson: Gson
) {

    @TypeConverter
    fun toListJson(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromListJson(json: String): List<String> {
        return gson.fromJson(json, object : TypeToken<List<String>>() {}.type) ?: emptyList()
    }
}