package com.example.noteappkt.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappkt.R
import com.example.noteappkt.data.local.entity.NoteEntity
import com.example.noteappkt.databinding.NoteItemPinnedBinding

class NotePinnedListAdapter(val entityListItem: List<NoteEntity>, val listener: CardClickListener) :
    RecyclerView.Adapter<NotePinnedListAdapter.CustomView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomView {
        val binding: NoteItemPinnedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.note_item_pinned, parent, false
        )

        return CustomView(binding)
    }

    override fun onBindViewHolder(holder: CustomView, position: Int) {
        holder.bind(entityListItem.get(position),listener)
    }

    override fun getItemCount(): Int = entityListItem.size

    inner class CustomView(val binding: NoteItemPinnedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteEntity: NoteEntity, listener: CardClickListener) {
            binding.pinnedtitle.text = noteEntity.noteItem.title
            binding.pinneddescription.text = noteEntity.noteItem.note
            binding.pinnedcardview.setCardBackgroundColor(Color.parseColor(noteEntity.noteItem.color))

            binding.imageFilterButton.setOnClickListener {
                listener.imageClickListener(it,noteEntity)
            }

            binding.pinnedcardview.setOnClickListener { listener.clickListener(noteEntity) }
            binding.executePendingBindings()
        }
    }
}