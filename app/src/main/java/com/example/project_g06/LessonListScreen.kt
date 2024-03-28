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

    val lessonList: MutableList<lessonList> = mutableListOf(
        lessonList(1, "Kotlin Basics", "2 hours",false , "https://youtu.be/xT8oP0wy-A0?si=nJiubv5RrcQzgjFQ", "Learn the fundamentals of Kotlin programming language."),
        lessonList(2, "Advanced Kotlin Features", "3 hours", false, "https://youtu.be/bDlZeOhZnEE?si=CRRR0zINuXGJGLnx", "Explore advanced features and concepts in Kotlin."),
        lessonList(3, "Android App Development with Kotlin", "4 hours 30 minutes", false, "https://youtu.be/XLt_moCoauw?si=V1kVehJd_2aIqfVh", "Build Android apps using Kotlin programming language."),
        lessonList(4, "Kotlin for Server-Side Development", "2 hours 30 minutes", false, "https://youtu.be/5KhJobIbOEQ?si=i8UCd3FK_rnW5Rim", "Learn how to use Kotlin for server-side development."),
        lessonList(5, "Kotlin for Web Development", "3 hours", false, "https://youtu.be/P6NvySn7vt8?si=JUdcJUnT14mLHseD", "Discover how Kotlin can be used for web development projects.")
    )
    lateinit var listadapter: lessonlistadapter
    var lessonItemOnClick = { lessonlistClass: lessonList ->

        val intent = Intent(this@LessonListScreen, LessonDetails::class.java)
        intent.putExtra("Extra_lesson", lessonlistClass)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonListScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listadapter = lessonlistadapter(lessonList, lessonItemOnClick)

        //UpdateSharedPreference
        val getvalue: MutableList<Boolean> = mutableListOf(false, false, false, false, false)
        var getCurrLesson: lessonList

        val get_sharedPreference = getSharedPreferences("STUDENT_DETAILS", MODE_PRIVATE)
        val get_position = get_sharedPreference.getInt("LessonPosition", 0)

        val getCompletedLessonList = get_sharedPreference.getString("LessonCompletedList", "")

        val type = object : TypeToken<MutableList<Boolean>>() {}.type

        if (get_position != 0) {
            if (getCompletedLessonList != "") {
                val gson = Gson()

                getvalue.clear()
                getvalue.addAll(gson.fromJson(getCompletedLessonList, type))

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