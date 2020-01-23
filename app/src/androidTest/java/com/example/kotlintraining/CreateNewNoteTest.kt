package com.example.kotlintraining

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard


@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest {

    @Rule @JvmField
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java)

    @Test
    fun createNewNote(){

        val course = DataManager.courses["adroid_async"]
        val noteTitle = "Title"
        val noteText = "Body"

        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.spinnerCourses)).perform(click())
        onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course))).perform(click())

        onView(withId(R.id.textNoteTitle)).perform(typeText(noteTitle))
        onView(withId(R.id.textNoteText)).perform(typeText(noteText), closeSoftKeyboard())

        pressBack()

        val newNote = DataManager.notes.last()
        assertEquals(course, newNote.course)
        assertEquals(noteTitle, newNote.title)
        assertEquals(noteText, newNote.text)

    }
}