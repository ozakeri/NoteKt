package com.example.noteappkt.repository

import com.example.noteappkt.data.local.NoteDao
import com.example.noteappkt.data.local.entity.NoteEntity
import com.example.noteappkt.data.local.model.NoteItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultNoteRepository @Inject constructor(val noteDao: NoteDao) : NoteRepository {

    override suspend fun insertNote(noteEntity: NoteEntity) {
        noteDao.insertNote(noteEntity)
    }

    override suspend fun deleteNote(noteEntity: NoteEntity) {
        noteDao.deleteNote(noteEntity)
    }

    override suspend fun updateNote(noteEntity: NoteEntity) {
        noteDao.updateNote(noteEntity)
    }

    override fun getAllNotes(): Flow<List<NoteEntity>> {
        return noteDao.getAllNotes()
    }
}