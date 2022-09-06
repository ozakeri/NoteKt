package com.example.noteappkt.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.noteappkt.R
import com.example.noteappkt.adapter.CardClickListener
import com.example.noteappkt.adapter.NoteListAdapter
import com.example.noteappkt.adapter.NotePinnedListAdapter
import com.example.noteappkt.data.local.entity.NoteEntity
import com.example.noteappkt.databinding.FragmentListBinding
import com.example.noteappkt.ui.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), CardClickListener {

    private lateinit var binding: FragmentListBinding
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        getUnPinnedNotes()
        getPinnedNotes()

        binding.addnoteFab.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_listFragment_to_addNoteFragment)
        }
        return binding.root
    }


    fun deleteNote(noteEntity: NoteEntity) {
        viewModel.deleteNote(noteEntity)
    }

    override fun clickListener(item: NoteEntity) {
        val bundle = Bundle()
        bundle.putParcelable("note", item)
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_listFragment_to_addNoteFragment, bundle)
    }

    override fun imageClickListener(image: View, noteEntity: NoteEntity) {
        val popupMenu = PopupMenu(requireActivity(), image)
        popupMenu.inflate(R.menu.actions)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
            if (it.itemId == R.id.dlt) {
                deleteNote(noteEntity)
                true
            } else return@OnMenuItemClickListener false
        })

        popupMenu.show()
    }

    fun getUnPinnedNotes() {
        viewModel.getAllNoteList.observe(this, Observer {
            val notes: ArrayList<NoteEntity> = ArrayList()
            it.forEach {
                if (!it.noteItem.pinned) {
                    notes.add(it)
                }
            }

            binding.upcomingRv.adapter = NoteListAdapter(notes, this)
        })
    }

    fun getPinnedNotes() {
        viewModel.getAllNoteList.observe(this, Observer {
            val notes: ArrayList<NoteEntity> = ArrayList()
            it.forEach {
                if (it.noteItem.pinned) {
                    notes.add(it)
                }
            }

            binding.pinnedRv.adapter = NotePinnedListAdapter(notes, this)
        })
    }

}