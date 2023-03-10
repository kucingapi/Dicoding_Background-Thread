package com.example.backgroundthread

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var quote = ""
    var author = ""

    fun storeQuote(quote: String, author: String){
        this.quote = quote
        this.author = author
    }
}