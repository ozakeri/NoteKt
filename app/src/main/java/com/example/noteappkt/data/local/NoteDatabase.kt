package com.example.noteappkt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteappkt.data.local.entity.NoteEntity
import com.example.noteappkt.other.converters.NoteItemConverter

@TypeConverters(NoteItemConverter::class)
@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}