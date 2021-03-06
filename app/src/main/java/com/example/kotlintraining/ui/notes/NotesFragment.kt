package com.example.kotlintraining.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintraining.DataManager
import com.example.kotlintraining.NoteInfo
import com.example.kotlintraining.NoteRecyclerAdapter
import com.example.kotlintraining.R
import com.example.kotlintraining.ui.recentNotes.RecentNotesViewModel
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.view.*

class NotesFragment : Fragment(), NoteRecyclerAdapter.OnNoteSelectedListener {

    private lateinit var recentNotesViewModel: RecentNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notes, container, false)
        val adapter = NoteRecyclerAdapter(activity, DataManager.notes)
        adapter.setOnSelectedListener(this)

        recentNotesViewModel = activity?.run {
            ViewModelProviders.of(this)[RecentNotesViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        root.listItems?.layoutManager = LinearLayoutManager(activity)
        root.listItems?.adapter = adapter

        return root
    }

    override fun onResume() {
        super.onResume()
        activity?.listItems?.adapter?.notifyDataSetChanged()
    }

    override fun onNoteSelected(note: NoteInfo) {
        recentNotesViewModel.addToRecentlyViewedNotes(note)
    }


}