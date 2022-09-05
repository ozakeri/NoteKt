package com.example.noteappkt.adapter

import android.view.View
import com.example.noteappkt.data.local.entity.NoteEntity

interface CardClickListener {

    fun clickListener(item: NoteEntity)

    fun imageClickListener(image : View, noteEntity: NoteEntity)
}