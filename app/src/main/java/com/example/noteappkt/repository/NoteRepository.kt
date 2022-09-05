package com.example.noteappkt.repository

import com.example.noteappkt.data.local.entity.NoteEntity
import com.example.noteappkt.data.local.model.NoteItem
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(noteEntity: NoteEntity)

    suspend fun deleteNote(noteEntity: NoteEntity)

    suspend fun updateNote(noteEntity: NoteEntity)

    fun getAllNotes(): Flow<List<NoteEntity>>
}