package com.example.project_g06

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g06.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //--------Code Start Here-------------------

        //getting student name from sharedPreferences
        val get_sharedPreference = getSharedPreferences("STUDENT_DETAILS", MODE_PRIVATE)
        val getStuName = get_sharedPreference.getString("stuName", "")

        if (getStuName != "") {
            val welcomeBackScreenIntent = Intent(this@MainActivity, WelcomeBackScreen::class.java)

            //putting student name in sharedPreference
            val putStuName = get_sharedPreference.edit()
            putStuName.putString("stuName", getStuName)
            putStuName.apply()

            welcomeBackScreenIntent.putExtra("Extra_stuName", getStuName)
            startActivity(welcomeBackScreenIntent)
        }

        binding.btnStuSignUP.setOnClickListener {
            val studentName = binding.etStudentName.text.toString()

            if (studentName == "") {
                val snackbar_fieldEmpty =
                    Snackbar.make(binding.root, "Please, Enter your name...", Snackbar.LENGTH_LONG)
                snackbar_fieldEmpty.show()
                return@setOnClickListener
            } else {

                val welcomeBackScreenIntent =
                    Intent(this@MainActivity, WelcomeBackScreen::class.java)

                //Putting student name in sharedPreference
                val putStuName = get_sharedPreference.edit()
                putStuName.putString("stuName", studentName)
                putStuName.apply()

                welcomeBackScreenIntent.putExtra("Extra_stuName", studentName)
                startActivity(welcomeBackScreenIntent)
            }
        }
    }
}