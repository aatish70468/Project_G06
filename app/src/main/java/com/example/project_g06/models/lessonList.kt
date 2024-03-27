package com.example.project_g06.models

class lessonList {
    var lessonNum: Int
    var lessonName: String
    var lessonLength: String
    var checkmark: Boolean

    constructor(lessonNum: Int, lessonName: String, lessonLength: String, checkmark: Boolean) {
        this.lessonNum = lessonNum
        this.lessonName = lessonName
        this.lessonLength = lessonLength
        this.checkmark = checkmark
    }
}