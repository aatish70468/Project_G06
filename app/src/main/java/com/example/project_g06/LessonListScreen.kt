package com.example.project_g06

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_g06.adapters.lessonlistadapter
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

    lateinit var listadapter: lessonlistadapter
    var lessonItemOnClick = { lessonlistClass: lessonList ->

        val intent = Intent(this@LessonListScreen, LessonDetails::class.java)
//        intent.putExtra("Extra_lesson", lessonlistClass)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listadapter = lessonlistadapter(lessonList, lessonItemOnClick)

        binding.listView.adapter = listadapter
        binding.listView.layoutManager = LinearLayoutManager(this)

        binding.listView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )

    }
}
