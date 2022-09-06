package com.example.noteappkt.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.noteappkt.R
import com.example.noteappkt.data.local.entity.NoteEntity
import com.example.noteappkt.data.local.model.NoteItem
import com.example.noteappkt.databinding.FragmentAddNoteBinding
import com.example.noteappkt.ui.MainActivity
import com.example.noteappkt.ui.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentAddNoteBinding
    private val viewmodel: NoteViewModel by viewModels()
    var colorValue: String = "#64C8FD"
    var pinned: Boolean = false
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)

        selectColor()

        binding.fabcheckBtn.setOnClickListener {

            val noteItem = NoteItem(
                binding.titleEdtx.text.toString(),
                binding.noteEdtx.text.toString(),
                colorValue,
                pinned
            )
            val noteEntity = NoteEntity(null, noteItem)
            viewmodel.insertToDb(noteEntity)

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        mainActivity.setSupportActionBar(toolbar)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun selectColor() {
        binding.color1.setOnClickListener {
            checkColorClicked(binding.check1)
        }

        binding.color2.setOnClickListener {
            checkColorClicked(binding.check2)
        }

        binding.color3.setOnClickListener {
            checkColorClicked(binding.check3)
        }

        binding.color4.setOnClickListener {
            checkColorClicked(binding.check4)
        }

        binding.color5.setOnClickListener {
            checkColorClicked(binding.check5)
        }

        binding.color6.setOnClickListener {
            checkColorClicked(binding.check6)
        }


    }

    fun checkColorClicked(clicked: View) {
        unCheckAll()

        clicked.visibility = View.VISIBLE

        binding.apply {
            when (clicked.id) {
                this.check1.id -> colorValue = "#64C8FD"
                this.check2.id -> colorValue = "#8069FF"
                this.check3.id -> colorValue = "#FFCC36"
                this.check4.id -> colorValue = "#D77FFD"
                this.check5.id -> colorValue = "#FF419A"
                this.check6.id -> colorValue = "#7FFB76"

            }
        }
    }

    fun unCheckAll() {
        binding.check1.visibility = View.GONE
        binding.check2.visibility = View.GONE
        binding.check3.visibility = View.GONE
        binding.check4.visibility = View.GONE
        binding.check5.visibility = View.GONE
        binding.check6.visibility = View.GONE
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.singlenote_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        println("===onMenuItemSelected==")

        when (menuItem.itemId) {
            R.id.pinitem -> {

                if (pinned) {
                    menuItem.icon =
                        ContextCompat.getDrawable(requireActivity(), R.drawable.pin_deselected)
                    pinned = false

                    println("===pinned1==" + pinned)

                } else {
                    menuItem.icon =
                        ContextCompat.getDrawable(requireActivity(), R.drawable.pin_selected)
                    pinned = true
                    println("===pinned==2" + pinned)
                }
                return true
            }
        }

        return true
    }

    override fun onPrepareMenu(menu: Menu) {
        super.onPrepareMenu(menu)

        val item = menu.findItem(R.id.pinitem)
        if (pinned) {
            item.icon =
                ContextCompat.getDrawable(requireActivity(), R.drawable.pin_selected)
            pinned = true

            println("===pinned1==" + pinned)

        } else {
            item.icon =
                ContextCompat.getDrawable(requireActivity(), R.drawable.pin_deselected)
            pinned = false
            println("===pinned==2" + pinned)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

}