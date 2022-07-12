package com.fauzimaulana.seucomapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BuildingCreatedResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: BuildingCreatedItemResponse,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class BuildingCreatedItemResponse(

	@field:SerializedName("locCreatedAt")
	val locCreatedAt: String,

	@field:SerializedName("locName")
	val locName: String,

	@field:SerializedName("locUpdatedUsr")
	val locUpdatedUsr: String,

	@field:SerializedName("locLongitude")
	val locLongitude: Double,

	@field:SerializedName("locUpdatedAt")
	val locUpdatedAt: String,

	@field:SerializedName("locDispensation")
	val locDispensation: Double,

	@field:SerializedName("locTypeLabel")
	val locTypeLabel: String,

	@field:SerializedName("locActiveLabel")
	val locActiveLabel: String,

	@field:SerializedName("locCode")
	val locCode: String,

	@field:SerializedName("locActive")
	val locActive: Boolean,

	@field:SerializedName("locType")
	val locType: String,

	@field:SerializedName("locLatitude")
	val locLatitude: Double,

	@field:SerializedName("locID")
	val locID: String
)
