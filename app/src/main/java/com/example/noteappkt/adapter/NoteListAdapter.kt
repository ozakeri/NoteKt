package com.example.noteappkt.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappkt.R
import com.example.noteappkt.data.local.entity.NoteEntity
import com.example.noteappkt.databinding.NoteItemBinding

class NoteListAdapter(
    private val noteEntityList: List<NoteEntity>,
    val listener: CardClickListener
) : RecyclerView.Adapter<NoteListAdapter.CustomView>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val binding: NoteItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.note_item, parent, false
        )

        return CustomView(binding)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        holder.bind(noteEntityList.get(position),listener)
    }

    override fun getItemCount(): Int = noteEntityList.size

    inner class CustomView(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(noteEntity: NoteEntity, listener: CardClickListener) {
            binding.pinnedtitle.text = noteEntity.noteItem.title
            binding.pinneddescription.text = noteEntity.noteItem.note
            //binding.upcomingCard.setCardBackgroundColor(Color.parseColor(noteEntity.noteItem.color))

            binding.imageFilterButton2.setOnClickListener {
                listener.imageClickListener(it,noteEntity)
            }
            binding.upcomingCard.setOnClickListener {
                listener.clickListener(noteEntity)
            }

            binding.executePendingBindings()
        }
    }
}