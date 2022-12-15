package dev.jameshill.simplenotes.recycler

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.jameshill.simplenotes.R
import dev.jameshill.simplenotes.db.Note
import android.view.LayoutInflater


class NoteItemAdapter : RecyclerView.Adapter<NoteItemAdapter.NoteItemViewHolder>() {
    var data = listOf<Note>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            NoteItemViewHolder = NoteItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class NoteItemViewHolder(val rootView: TextView) : RecyclerView.ViewHolder(rootView) {

        companion object {
            fun inflateFrom(parent: ViewGroup): NoteItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.note_item, parent, false) as TextView
                return NoteItemViewHolder(view)
            }
        }

        fun bind(item: Note) {
            rootView.text = item.memo
        }
    }
}