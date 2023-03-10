package com.example.backgroundthread

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel: ViewModel() {
    var quote = ""
    var author = ""
    private val mElapsedTime = MutableLiveData<Long?>()

    fun storeQuote(quote: String, author: String){
        this.quote = quote
        this.author = author
    }

    companion object {
        private const val ONE_SECOND = 1000
    }
    private val mInitialTime = SystemClock.elapsedRealtime()

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }
    fun getElapsedTime(): LiveData<Long?> {
        return mElapsedTime
    }
}