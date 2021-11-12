package com.tiyas.mylivedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class MainViewModel : ViewModel() {

//     ini metode untuk menjalankan timer
    companion object{
        private const val ONE_SECOND = 1000
    }

    private val mInitialTime = SystemClock.elapsedRealtime()

//     nah ini untuk objek livedata yang nantinya akan di subscribe oleh MainActivity
    private  val mElapsedTime = MutableLiveData<Long?>()
    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object  : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
//                 untuk objek livedata yg akan di subscribe oleh MainActivity
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }
//ini untuk objek live datanya .
    fun getElapsedTime() : LiveData<Long?>{
        return mElapsedTime
    }
}