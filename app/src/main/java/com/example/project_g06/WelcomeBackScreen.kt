package com.example.project_g06

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g06.databinding.ActivityWelcomeBackScreenBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WelcomeBackScreen : AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBackScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBackScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //----------Code Start Here-------------

        if (intent == null) {
            //creating snackbar if the there is not intent variable in the screen
            val snackbar = Snackbar.make(binding.root, "ERROR: No Intent Found", Snackbar.LENGTH_LONG)
            snackbar.show()
        } else {

            //creating lessonCompletedList by student and byDefault is all lessons is not completed
            val lessonCompletedList: MutableList<Boolean> = mutableListOf(false, false, false, false, false)

            //getting Lesson Completed List sharedPreferences
            val get_sharedPreference = getSharedPreferences("STUDENT_DETAILS", MODE_PRIVATE)
            val getCompletedLessonList = get_sharedPreference.getString("LessonCompletedList", "")

            //creating Gson and type variable for getting data from sharedPreference
            val gson = Gson()
            val type = object : TypeToken<MutableList<Boolean>>() {}.type

            //checking getCompletedLessonList is Empty or not
            if (getCompletedLessonList != "") {
                //deleting all values from lessonCompletedList and adding new values from sharedPreference
                lessonCompletedList.clear()
                lessonCompletedList.addAll(gson.fromJson(getCompletedLessonList, type))
            }

            //setting student name by getting name from MainActivity Screen
            val getStuName = intent.getStringExtra("Extra_stuName")
            binding.tvDisplayStuName.text = "Welcome Back, ${getStuName}"

            //setting completed lesson in form of percentage and numbers

            //creating variable for storing true values
            var number_lesson_completed = 0
            var percentage_lesson_completed = 0.0

            for (currItem in lessonCompletedList) {
                if (currItem == true) {
                    //increment in value
                    number_lesson_completed += 1
                }
            }

            //creating variable for storing percentage of lessons completed
            percentage_lesson_completed = number_lesson_completed.toDouble() / lessonCompletedList.size
            percentage_lesson_completed *= 100

            //setting values in layout
            binding.tvCourseCompletedPercentage.text = "You have completed ${percentage_lesson_completed}% of your course"
            binding.tvNumberLessonCompleted.text = "Lessons Completed: ${number_lesson_completed}"
            binding.tvNumberLessonRemaining.text = "Lessons Remaining: ${lessonCompletedList.size - number_lesson_completed}"

            //moving student to next Lesson List Screen
            binding.btnScreenLessonList.setOnClickListener {
                val lessonListIntent = Intent(this@WelcomeBackScreen, LessonListScreen::class.java)
                startActivity(lessonListIntent)
            }

            //deleting student account and clearing all sharedPreferences
            binding.btnDeleteAccount.setOnClickListener {
                //Delete account from shared preference
                val get_sharedPreference = getSharedPreferences("STUDENT_DETAILS", MODE_PRIVATE)
                get_sharedPreference.edit().clear().apply()
                val mainPageIntent = Intent(this@WelcomeBackScreen, MainActivity::class.java)
                startActivity(mainPageIntent)
            }
        }
    }
}