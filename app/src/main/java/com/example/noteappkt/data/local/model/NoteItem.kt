package com.example.noteappkt.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity

data class NoteItem(
    val title: String,
    val note: String,
    val color: String,
    val pinned: Boolean
)
