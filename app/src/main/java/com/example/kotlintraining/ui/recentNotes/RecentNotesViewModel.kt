package com.example.kotlintraining.ui.recentNotes

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlintraining.DataManager
import com.example.kotlintraining.NoteInfo

class RecentNotesViewModel : ViewModel() {
    var isNewlyCreated = true

    private val maxRecentlyViewedNotes = 5
    val recentlyViewedNotes = ArrayList<NoteInfo>(maxRecentlyViewedNotes)

    fun addToRecentlyViewedNotes(note: NoteInfo) {
//         Check if selection is already in the list
        val existingIndex = recentlyViewedNotes.indexOf(note)
        if (existingIndex == -1) {
            // it isn't in the list...
            // Add new one to beginning of list and remove any beyond max we want to keep
            recentlyViewedNotes.add(0, note)
            for (index in recentlyViewedNotes.lastIndex downTo maxRecentlyViewedNotes)
                recentlyViewedNotes.removeAt(index)
        } else {
            // it is in the list...
            // Shift the ones above down the list and make it first member of the list
            for (index in (existingIndex - 1) downTo 0)
                recentlyViewedNotes[index + 1] = recentlyViewedNotes[index]
            recentlyViewedNotes[0] = note
        }
    }

    fun saveState(outState: Bundle) {
        val noteIds = DataManager.noteIdsAsIntArray(recentlyViewedNotes)
        outState.putIntArray("NOTE_IDS", noteIds)
    }

    fun restoreState(savedInstanceState: Bundle) {
        val noteIds = savedInstanceState.getIntArray("NOTE_IDS")
        if (noteIds != null) {
            val noteList = DataManager.loadNotes(*noteIds)
            recentlyViewedNotes.addAll(noteList)
        }
    }
}