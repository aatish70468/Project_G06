package com.example.project_g06.models

import java.io.Serializable

class lessonList: Serializable {
    var lessonNum: Int
    var lessonName: String
    var lessonLength: String
    var checkmark: Boolean
    var lessonVideo: String
    var description: String

    constructor(
        lessonNum: Int,
        lessonName: String,
        lessonLength: String,
        checkmark: Boolean,
        lessonVideo: String,
        description: String
    ) {
        this.lessonNum = lessonNum
        this.lessonName = lessonName
        this.lessonLength = lessonLength
        this.checkmark = checkmark
        this.lessonVideo = lessonVideo
        this.description = description
    }
}