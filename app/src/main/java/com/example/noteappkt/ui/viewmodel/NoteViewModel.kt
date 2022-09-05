package com.example.noteappkt.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappkt.data.local.entity.NoteEntity
import com.example.noteappkt.data.local.model.NoteItem
import com.example.noteappkt.repository.DefaultNoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val defaultNoteRepository: DefaultNoteRepository) :
    ViewModel() {

    val _getAllNoteList: MutableLiveData<List<NoteEntity>> = MutableLiveData()
    val getAllNoteList: LiveData<List<NoteEntity>> = _getAllNoteList

    init {
        getAllNotes()
    }

    fun insertToDb(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            defaultNoteRepository.insertNote(noteEntity)
        }
    }

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            defaultNoteRepository.deleteNote(noteEntity)
        }
    }

    fun updateNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            defaultNoteRepository.updateNote(noteEntity)
        }
    }

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            defaultNoteRepository.getAllNotes().collect {
                _getAllNoteList.postValue(it)
            }
        }

    }

}