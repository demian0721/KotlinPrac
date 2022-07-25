package com.example.parkpromobile.ui.lessonRoom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LessonRoomViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is LessonRoom Fragment"
    }
    val text: LiveData<String> = _text
}