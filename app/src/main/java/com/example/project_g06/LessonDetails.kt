package com.example.project_g06

import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g06.databinding.ActivityLessonDetailsBinding

class LessonDetails : AppCompatActivity() {

    private lateinit var binding: ActivityLessonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example data (replace with actual data retrieval)
        val lessonName = "Variables and Conditionals"
        val lessonNumber = "Lesson 1"
        val lessonDescription = "In this module, you will learn about variables and conditionals in Javascript. Variables are important because they allow you to programmatically store data. Conditionals are useful for making choices."
        val lessonLength = "1 hour 20 min"

        // Bind data to UI elements
        binding.textViewLessonName.text = "$lessonName - $lessonNumber"
        binding.textViewDescription.text = lessonDescription
        binding.textViewLength.text = "Length: $lessonLength"

        // Button click listeners
        binding.buttonWatchLesson.setOnClickListener {
            // Load lesson in WebView (replace with actual URL)
            loadLessonInWebView("https://www.example.com/lesson1")
        }

        binding.buttonMarkComplete.setOnClickListener {
            // Mark lesson as complete
            markLessonComplete()
        }
    }

    private fun loadLessonInWebView(url: String) {
        // Show WebView and load URL
        binding.webViewLesson.visibility = WebView.VISIBLE
        binding.webViewLesson.loadUrl(url)
    }

    private fun markLessonComplete() {
        // Mark lesson as complete (replace with actual implementation)
        // Update lesson completion status in SharedPreferences
        // For example:
        // val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        // sharedPreferences.edit().putBoolean("lesson1_completed", true).apply()
    }
}