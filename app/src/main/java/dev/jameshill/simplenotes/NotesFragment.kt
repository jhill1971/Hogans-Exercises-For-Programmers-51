package dev.jameshill.simplenotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.jameshill.simplenotes.databinding.FragmentNotesBinding
import dev.jameshill.simplenotes.db.NoteDataBase
import dev.jameshill.simplenotes.recycler.NoteItemAdapter
import dev.jameshill.simplenotes.vm.NotesViewModel
import dev.jameshill.simplenotes.vm.NotesViewModelFactory


class NotesFragment : Fragment() {


    /* A nullable variable that is used to store the binding object. */
    private var _binding: FragmentNotesBinding? = null
    /* This is a getter that is used to access the binding object. */
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val view = binding.root

        /* Build the db if it doesn't already exist, and get a reference to the note dao property */
        val application = requireNotNull(this.activity).application
        val dao = NoteDataBase.getInstance(application).noteDao
        val viewModelFactory = NotesViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(NotesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = NoteItemAdapter()
        binding.notesList.adapter = adapter
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}