package com.example.project_g06

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g06.databinding.ActivityLessonDetailsBinding
import com.example.project_g06.models.lessonList
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LessonDetails : AppCompatActivity() {

    lateinit var binding: ActivityLessonDetailsBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var checkvalue: MutableList<Boolean> = mutableListOf(false, false, false, false, false)

        if (intent == null) {
            val snackbar = Snackbar.make(binding.root, "ERROR: No Intent Found", Snackbar.LENGTH_LONG)
            snackbar.show()
        } else {

            val lesson = intent.getSerializableExtra("Extra_lesson") as lessonList
            sharedPreferences = getSharedPreferences("STUDENT_DETAILS", Context.MODE_PRIVATE)

            var get_position = sharedPreferences.getInt("LessonPosition", 0)
            var getCompletedLessonList = sharedPreferences.getString("LessonCompletedList", "")

            val gson = Gson()
            val type = object : TypeToken<MutableList<Boolean>>() {}.type

            if (get_position != 0) {
                if (getCompletedLessonList != "") {
                    checkvalue.replaceAll(gson.fromJson(getCompletedLessonList, type))
                }
            }

            binding.textViewLessonName.text = "${lesson.lessonName} - Lesson ${lesson.lessonNum}"
            binding.textViewDescription.text = lesson.description
            binding.textViewLength.text = "Length: ${lesson.lessonLength}"

            binding.buttonWatchLesson.setOnClickListener {
                binding.webViewLesson.loadUrl(lesson.lessonVideo)
            }

            binding.buttonMarkComplete.setOnClickListener {
                checkvalue[lesson.lessonNum - 1] = true
                val json = gson.toJson(checkvalue)
                sharedPreferences.edit().putInt("LessonPosition",lesson.lessonNum).apply()
                sharedPreferences.edit().putString("LessonCompletedList",json).apply()
                val goBack = Intent(this@LessonDetails,LessonListScreen::class.java)
                startActivity(goBack)
            }
        }
    }
}