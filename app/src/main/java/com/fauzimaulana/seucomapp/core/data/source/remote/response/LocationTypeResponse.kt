package com.fauzimaulana.seucomapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LocationTypeResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: LocationResponse,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class LocationResponse(

	@field:SerializedName("PR")
	val pR: String,

	@field:SerializedName("BD")
	val bD: String,

	@field:SerializedName("FL")
	val fL: String,

	@field:SerializedName("RO")
	val rO: String
)
