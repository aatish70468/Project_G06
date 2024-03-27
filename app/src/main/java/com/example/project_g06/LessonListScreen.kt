package com.example.project_g06

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_g06.databinding.ActivityLessonListScreenBinding
import com.example.project_g06.databinding.ActivityMainBinding
import com.example.project_g06.models.lessonList

class LessonListScreen : AppCompatActivity() {

    lateinit var binding: ActivityLessonListScreenBinding
    var lessonList: MutableList<lessonList> = mutableListOf(
        lessonList(1, "Introduction to the course", "1hr 20min", false),
        lessonList(2, "Introduction to the course", "1hr 20min", false),
        lessonList(3, "Introduction to the course", "1hr 20min", false),
        lessonList(4, "Introduction to the course", "1hr 20min", false),
        lessonList(5, "Introduction to the course", "1hr 20min", false)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lesson_list_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}