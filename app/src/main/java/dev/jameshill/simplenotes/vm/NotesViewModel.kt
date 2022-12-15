package dev.jameshill.simplenotes.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jameshill.simplenotes.db.Note
import dev.jameshill.simplenotes.db.NoteDao
import kotlinx.coroutines.launch

/* This class contains a live data object that represents the list of notes in the db */
class NotesViewModel (val dao: NoteDao): ViewModel() {
    /* The class declaration passes a NoteDao object to the viewModel.
    * This class extends the ViewModel class.*/

    var newNote = ""

    val notes = dao.getAll()


    fun addNote() {
        viewModelScope.launch {
            val note = Note()
            note.memo = newNote
            dao.insert(note)
        }
    }
}