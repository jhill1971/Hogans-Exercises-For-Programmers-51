package dev.jameshill.simplenotes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/* This class sets up the database table and its columns */
@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey (autoGenerate = true)
    var noteId: Long = 0L,
    @ColumnInfo (name = "note_text")
    var memo: String = ""
)
