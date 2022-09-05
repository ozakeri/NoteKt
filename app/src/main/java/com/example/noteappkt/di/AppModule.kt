package com.example.noteappkt.di

import android.content.Context
import androidx.room.Room
import com.example.noteappkt.data.local.NoteDao
import com.example.noteappkt.data.local.NoteDatabase
import com.example.noteappkt.repository.DefaultNoteRepository
import com.example.noteappkt.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, "note.db")
            .build()

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.getNoteDao()

    @Provides
    @Singleton
    fun provideDefaultNoteRepository(noteDao: NoteDao) =
        DefaultNoteRepository(noteDao) as NoteRepository
}