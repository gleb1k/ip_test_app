package ru.glebik.core.database

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class LocalDateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { convertToLocalDate(it) }
    }

    @TypeConverter
    fun toTimestamp(value: LocalDateTime?): Long? {
        return value?.let { convertToTimestamp(it) }
    }

    private fun convertToTimestamp(time: LocalDateTime?): Long {
        if (time == null) return -1
        return time.atOffset(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli()
    }

    private fun convertToLocalDate(time: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneOffset.UTC)
    }
}