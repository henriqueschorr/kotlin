package com.example.kotlintraining.ui.recentNotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintraining.CourseRecyclerAdapter
import com.example.kotlintraining.DataManager
import com.example.kotlintraining.R
import com.example.kotlintraining.RecentNotesRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.view.*
import kotlinx.android.synthetic.main.item_note.*

class RecentNotesFragment : Fragment() {

    private lateinit var recentNotesViewModel: RecentNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_recent_notes, container, false)

        recentNotesViewModel = activity?.run {
            ViewModelProviders.of(this)[RecentNotesViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        root.listItems?.layoutManager = LinearLayoutManager(activity)

        root.listItems?.adapter = RecentNotesRecyclerAdapter(activity, recentNotesViewModel.recentlyViewedNotes)
        return root
    }
}