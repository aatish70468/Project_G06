package com.example.project_g06

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_g06.adapters.lessonlistadapter
import com.example.project_g06.databinding.ActivityLessonListScreenBinding
import com.example.project_g06.models.lessonList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LessonListScreen : AppCompatActivity() {

    lateinit var binding: ActivityLessonListScreenBinding
    var lessonList: MutableList<lessonList> = mutableListOf(
        lessonList(1, "Introduction to the course", "Length: 1hr 20min", false),
        lessonList(2, "Introduction to the course", "Length: 1hr 20min", false),
        lessonList(3, "Introduction to the course", "Length: 1hr 20min", false),
        lessonList(4, "Introduction to the course", "Length: 1hr 20min", false),
        lessonList(5, "Introduction to the course", "Length: 1hr 20min", false)
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

        //UpdateSharedPreference
        var getvalue: MutableList<Boolean> = mutableListOf(false, false, false, false, false)
        var getCurrLesson: lessonList

        val get_sharedPreference = getSharedPreferences("STUDENT_DETAILS", MODE_PRIVATE)
        var get_position = get_sharedPreference.getInt("LessonPosition", 0)

        var getCompletedLessonList = get_sharedPreference.getString("LessonCompletedList", "")

        var gson = Gson()
        val type = object : TypeToken<MutableList<Boolean>>() {}.type

        if (get_position != 0) {
            if (getCompletedLessonList != "") {
                getvalue.replaceAll(gson.fromJson(getCompletedLessonList, type))


                for ((index, currStatus) in getvalue.withIndex()){
                    getCurrLesson = lessonList.get(index)

                    getCurrLesson.checkmark = currStatus
                }
            }
        }


        binding.listView.adapter = listadapter
        binding.listView.layoutManager = LinearLayoutManager(this)

        binding.listView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )

    }
}
