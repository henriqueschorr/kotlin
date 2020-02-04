package com.example.kotlintraining

object DataManager {

    val courses = HashMap<String, CourseInfo>();
    val notes = ArrayList<NoteInfo>();

    init {
        initializeCourses()
        initializeNotes()
    }

    fun addNote(course: CourseInfo, noteTitle: String, noteText: String): Int {
        val note = NoteInfo(course, noteTitle, noteText)
        notes.add(note)
        return notes.lastIndex
    }

    fun findNote(course: CourseInfo, noteTitle: String, noteText: String) : NoteInfo?{
        for(note in notes)
            if(course == note.course && noteTitle == note.title && noteText == note.text)
                return note

        return null
    }

    private fun simulateLoadDelay() {
        Thread.sleep(1000)
    }

    fun loadNotes(vararg noteIds: Int): List<NoteInfo> {
        simulateLoadDelay()
        val noteList: List<NoteInfo>

        if(noteIds.isEmpty())
            noteList = notes
        else {
            noteList = ArrayList<NoteInfo>(noteIds.size)
            for(noteId in noteIds)
                noteList.add(notes[noteId])
        }
        return noteList
    }

    fun idOfNote(note: NoteInfo) = notes.indexOf(note)

    fun noteIdsAsIntArray(notes: List<NoteInfo>): IntArray {
        val noteIds = IntArray(notes.size)
        for(index in 0..notes.lastIndex)
            noteIds[index] = DataManager.idOfNote(notes[index])
        return noteIds
    }

    private fun initializeCourses() {
        var course = CourseInfo("course1", "Course 1");
        courses.set(course.courseId, course);

        course = CourseInfo("course2", "Course 2");
        courses.set(course.courseId, course);

        course = CourseInfo("course3", "Course 3");
        courses.set(course.courseId, course);
    }

    private fun initializeNotes(){
        var course = courses["course1"]
        var note = NoteInfo(course, "Note 1", "Text 1")
        notes.add(note)

        course = courses["course2"]
        note = NoteInfo(course, "Note 2", "Text 2")
        notes.add(note)

        course = courses["course3"]
        note = NoteInfo(course, "Note 3", "Text 3")
        notes.add(note)

        course = courses["course1"]
        note = NoteInfo(course, "Note 4", "Text 4")
        notes.add(note)

        course = courses["course2"]
        note = NoteInfo(course, "Note 5", "Text 5")
        notes.add(note)

        course = courses["course3"]
        note = NoteInfo(course, "Note 6", "Text 6")
        notes.add(note)

        course = courses["course1"]
        note = NoteInfo(course, "Note 7", "Text 7")
        notes.add(note)

        course = courses["course2"]
        note = NoteInfo(course, "Note 8", "Text 8")
        notes.add(note)
    }

}