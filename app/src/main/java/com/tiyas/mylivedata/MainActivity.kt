package com.tiyas.mylivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tiyas.mylivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  mLiveDataTimerViewModel: MainViewModel
    private lateinit var  mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mLiveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]
//        jika terjadi perubahan data makaa akan berubah juga textviewnya
        subscribe()
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long?> {
            aLong -> val newText = this@MainActivity.resources.getString(R.string.seconds, aLong)
            mainBinding.timerTextview.text = newText
        }
        mLiveDataTimerViewModel.getElapsedTime().observe(this
        ,elapsedTimeObserver)
    }
}