package com.example.backgroundthread

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("random")
    fun getQuote(): Call<QuoteResponse>
}