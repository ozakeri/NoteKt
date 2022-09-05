package com.example.noteappkt.other.converters

import androidx.room.TypeConverter
import com.example.noteappkt.data.local.model.NoteItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class NoteItemConverter {


    @TypeConverter
    fun tojson(noteItem: NoteItem): String? {
        if (noteItem == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<NoteItem?>() {}.getType()
        return gson.toJson(noteItem, type)
    }

    @TypeConverter
    fun toDataClass(note: String?): NoteItem? {
        if (note == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<NoteItem?>() {}.getType()
        return gson.fromJson(note, type)
    }
}