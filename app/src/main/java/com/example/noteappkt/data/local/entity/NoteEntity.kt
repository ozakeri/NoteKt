package com.example.noteappkt.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteappkt.data.local.model.NoteItem

@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val noteItem: NoteItem
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        TODO("noteItem")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteEntity> {
        override fun createFromParcel(parcel: Parcel): NoteEntity {
            return NoteEntity(parcel)
        }

        override fun newArray(size: Int): Array<NoteEntity?> {
            return arrayOfNulls(size)
        }
    }
}
