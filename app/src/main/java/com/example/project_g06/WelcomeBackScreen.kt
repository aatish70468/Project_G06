package com.example.project_g06

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_g06.databinding.ActivityMainBinding
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
//                val lessonListIntent = Intent(this@WelcomeBackScreen, LessonListScreen::class.java)
//                startActivity(lessonListIntent)
            }

            binding.btnDeleteAccount.setOnClickListener {
                //Delete account from shared preference
            }
        }
    }
}