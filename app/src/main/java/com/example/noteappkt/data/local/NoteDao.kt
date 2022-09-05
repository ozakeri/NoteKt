package com.example.noteappkt.data.local

import androidx.room.*
import androidx.room.Dao
import com.example.noteappkt.data.local.entity.NoteEntity
import com.example.noteappkt.data.local.model.NoteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): Flow<List<NoteEntity>>
}