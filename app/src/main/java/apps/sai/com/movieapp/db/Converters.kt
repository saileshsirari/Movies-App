package apps.sai.com.movieapp.db

import androidx.room.TypeConverter
import apps.sai.com.movieapp.data.Genre
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken




class Converters {
    @TypeConverter
    fun restoreIntList(listOfInt: String?): ArrayList<Int?>? {
        return Gson().fromJson(listOfInt, object : TypeToken<ArrayList<Int?>?>() {}.type)
    }

    @TypeConverter
    fun saveIntList(listOfInt: ArrayList<Int?>?): String? {
        return Gson().toJson(listOfInt)
    }

    @TypeConverter
    fun restoreGenres(list: String?): ArrayList<Genre?>? {
        return Gson().fromJson(list, object : TypeToken<ArrayList<Genre?>?>() {}.type)
    }

    @TypeConverter
    fun saveGenres(list: ArrayList<Genre?>?): String? {
        return Gson().toJson(list)
    }
}