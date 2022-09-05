package com.example.noteappkt.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.noteappkt.R
import com.example.noteappkt.data.local.model.NoteItem
import com.example.noteappkt.databinding.FragmentAddNoteBinding
import com.example.noteappkt.ui.viewmodel.NoteViewModel

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private val viewmodel : NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)

        binding.fabcheckBtn.setOnClickListener{

        }

        return binding.root
    }

}