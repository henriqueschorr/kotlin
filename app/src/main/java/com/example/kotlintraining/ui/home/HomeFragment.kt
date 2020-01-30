package com.example.kotlintraining.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlintraining.DataManager
import com.example.kotlintraining.NoteRecyclerAdapter
import com.example.kotlintraining.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        root.listItems?.layoutManager = LinearLayoutManager(activity)
        root.listItems?.adapter = NoteRecyclerAdapter(activity, DataManager.notes)

        return root
    }

    override fun onResume() {
        super.onResume()
        activity?.listItems?.adapter?.notifyDataSetChanged()
    }

}