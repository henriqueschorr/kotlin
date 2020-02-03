package com.example.kotlintraining.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintraining.DataManager
import com.example.kotlintraining.NoteRecyclerAdapter
import com.example.kotlintraining.R
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.view.*

class NotesFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notes, container, false)

        root.listItems?.layoutManager = LinearLayoutManager(activity)
        root.listItems?.adapter = NoteRecyclerAdapter(activity, DataManager.notes)
        root.listItems?.adapter

        return root
    }

    override fun onResume() {
        super.onResume()
        activity?.listItems?.adapter?.notifyDataSetChanged()
    }

}