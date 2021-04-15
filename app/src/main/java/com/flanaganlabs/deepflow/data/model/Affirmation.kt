package com.flanaganlabs.deepflow.data.model

import com.google.gson.annotations.SerializedName

data class Affirmation(

	@field:SerializedName("heading")
	val heading: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
