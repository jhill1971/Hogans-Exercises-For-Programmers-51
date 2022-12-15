package dev.jameshill.simplenotes.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.jameshill.simplenotes.db.NoteDao

class NotesViewModelFactory(private val dao: NoteDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)){
            return NotesViewModel(dao) as T
        }

        throw java.lang.IllegalArgumentException("Unknown ViewModel")
    }
}