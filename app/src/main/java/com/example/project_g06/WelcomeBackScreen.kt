package com.example.project_g06

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g06.databinding.ActivityWelcomeBackScreenBinding
import com.google.android.material.snackbar.Snackbar

class WelcomeBackScreen : AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBackScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBackScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //----------Code Start Here-------------

        //updating lessons details using shared preference is remaining

        if (intent == null) {
            val snackbar = Snackbar.make(binding.root, "ERROR: No Intent Found", Snackbar.LENGTH_LONG)
            snackbar.show()
        } else {

            val getStuName = intent.getStringExtra("Extra_stuName")

            binding.tvDisplayStuName.text = "Welcome Back, ${getStuName}"

            binding.btnScreenLessonList.setOnClickListener {
                val lessonListIntent = Intent(this@WelcomeBackScreen, LessonListScreen::class.java)
                startActivity(lessonListIntent)
            }

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