package com.example.kotlintraining

object DataManager {

    val courses = HashMap<String, CourseInfo>();
    val notes = ArrayList<NoteInfo>();

    init {
        initializeCourses()
//        initializeNotes()
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

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Intents");
        courses.set(course.courseId, course);

        course = CourseInfo("adroid_async", "Android Async");
        courses.set(course.courseId, course);
    }

    private fun initializeNotes(){
        var course = CourseInfo("android_intents", "Android Intents");
        var note = NoteInfo(course, "Teste", "Testando")
        notes.add(note)

        course = CourseInfo("adroid_async", "Android Async");
        note = NoteInfo(course, "Teste2", "Testando2")
        notes.add(note)
    }

}