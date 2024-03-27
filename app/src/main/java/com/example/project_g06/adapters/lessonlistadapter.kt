package com.example.project_g06.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_g06.R
import com.example.project_g06.models.lessonList

class lessonlistadapter(private val lessonListData: List<lessonList>, var sendLessonDeatils: (lessonList) -> Unit ) :
    RecyclerView.Adapter<lessonlistadapter.LessonListViewHolder>() {

    class LessonListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lessonNum: ImageView = itemView.findViewById(R.id.lessonnum)
        val LessonName: TextView = itemView.findViewById(R.id.tv1)
        val LessonLength: TextView = itemView.findViewById(R.id.tv2)
        val Checkmark: ImageView = itemView.findViewById(R.id.checkmark)
        val LessonBtn: LinearLayout = itemView.findViewById(R.id.lessonBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonListViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lesson_list_layout, parent, false)
        return LessonListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lessonListData.size
    }

    override fun onBindViewHolder(holder: LessonListViewHolder, position: Int) {
        val Lesson= lessonListData[position]

        holder.LessonName.text = Lesson.lessonName
        holder.LessonLength.text = Lesson.lessonLength
        holder.Checkmark.visibility = if (Lesson.checkmark) View.VISIBLE else View.GONE

        holder.lessonNum.setBackgroundResource(R.drawable.number1)

        holder.LessonBtn.setOnClickListener{
            sendLessonDeatils(Lesson)
        }
    }
}
