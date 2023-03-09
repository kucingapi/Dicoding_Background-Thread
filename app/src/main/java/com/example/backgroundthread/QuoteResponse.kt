package com.example.backgroundthread

import com.google.gson.annotations.SerializedName

data class QuoteResponse(

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("en")
	val en: String,

	@field:SerializedName("id")
	val id: String
)
