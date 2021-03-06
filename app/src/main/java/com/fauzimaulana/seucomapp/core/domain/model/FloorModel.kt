package com.fauzimaulana.seucomapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FloorModel(
    val locCreatedAt: String,
    val locName: String,
    val locUpdatedUsr: String,
    val locLongitude: Double,
    val locUpdatedAt: String,
    val locDispensation: Double,
    val locTypeLabel: String,
    val locActiveLabel: String,
    val locCode: String,
    val locActive: Boolean,
    val locType: String,
    val locLatitude: Double,
    val locID: String
): Parcelable
