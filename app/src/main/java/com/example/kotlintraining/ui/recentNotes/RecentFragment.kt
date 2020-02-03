package com.example.kotlintraining.ui.recentNotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlintraining.CourseRecyclerAdapter
import com.example.kotlintraining.DataManager
import com.example.kotlintraining.R
import kotlinx.android.synthetic.main.fragment_notes.view.*

class RecentFragment : Fragment() {

    private lateinit var coursesViewModel: RecentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        coursesViewModel =
            ViewModelProviders.of(this).get(RecentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_courses, container, false)
//        val textView: TextView = root.findViewById(R.id.text_gallery)
//        coursesViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

        root.listItems?.layoutManager = GridLayoutManager(activity, 2)
        root.listItems?.adapter = CourseRecyclerAdapter(activity, DataManager.courses.values.toList())
        return root
    }
}