package com.example.notes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes: List<Notes>, context: Context): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val db: NotesDatabaseHelper = NotesDatabaseHelper(context)


        class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
            val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
            val editBtn: ImageView = itemView.findViewById(R.id.editBtn)
            val deleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        holder.editBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, EditNoteActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteBtn.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getNotes())
            Toast.makeText(holder.itemView.context, "Note Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newNotes: List<Notes>){
        notes = newNotes
        notifyDataSetChanged()
    }

}