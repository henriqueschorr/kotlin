package com.example.kotlintraining

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DataManagerTest {

    @Before
    fun setUp(){
        DataManager.notes.clear()
    }

    @Test
    fun addNote(){
        val course = CourseInfo("adroid_async", "Android Async");
        val noteTitle = "test"
        val noteText = "Testing"

        val index = DataManager.addNote(course, noteTitle, noteText)
        val note = DataManager.notes[index]

        assertEquals(course, note.course)
        assertEquals(noteTitle, note.title)
        assertEquals(noteText, note.text)
    }

    @Test
    fun findSimilarNotes(){
        val course = CourseInfo("adroid_async", "Android Async")
        val noteTitle = "test"
        val noteText1 = "Testing1"
        val noteText2 = "Testing2"

        val index1 = DataManager.addNote(course, noteTitle, noteText1)
        val index2 = DataManager.addNote(course, noteTitle, noteText2)

        val note1 = DataManager.findNote(course, noteTitle, noteText1)
        val foundIndex1 = DataManager.notes.indexOf(note1)
        assertEquals(index1, foundIndex1)

        val note2 = DataManager.findNote(course, noteTitle, noteText2)
        val foundIndex2 = DataManager.notes.indexOf(note2)
        assertEquals(index2, foundIndex2)

    }
}