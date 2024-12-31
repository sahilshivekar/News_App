package com.learn.mynewsapp.data.local.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.learn.mynewsapp.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id}, ${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(',').let { sourceList ->
            Source(id = sourceList[0], name = sourceList[1])
        }
    }
}