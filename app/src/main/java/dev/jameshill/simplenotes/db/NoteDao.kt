package dev.jameshill.simplenotes.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/* This interface defines the data access methods for the database */
@Dao
interface NoteDao {

    @Insert
    suspend fun insert (note: Note)

    @Update
    suspend fun update (note: Note)

    @Delete
    suspend fun delete (note: Note)

    @Query("SELECT * FROM notes_table WHERE noteId = :noteId")
    fun get(noteId: Long): LiveData<Note>

    @Query("SELECT * FROM notes_table ORDER BY noteId ASC")
    fun getAll(): LiveData<List<Note>>
}