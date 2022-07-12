package com.fauzimaulana.seucomapp.core.utils

import com.fauzimaulana.seucomapp.core.data.source.remote.response.BuildingCreatedItemResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.response.LocationResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.response.ProjectCreatedItemResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.response.ProjectItem
import com.fauzimaulana.seucomapp.core.domain.model.BuildingCreatedModel
import com.fauzimaulana.seucomapp.core.domain.model.LocationTypeModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectCreatedModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectModel

object DataMapper {
    fun mapProjectResponseToDomain(input: List<ProjectItem>): List<ProjectModel> =
        input.map {
            ProjectModel(
                locActive = it.locActive,
                locActiveLabel = it.locActiveLabel,
                locCode = it.locCode,
                locCreatedAt = it.locCreatedAt,
                locDispensation = it.locDispensation,
                locID = it.locID,
                locLatitude = it.locLatitude,
                locLongitude = it.locLongitude,
                locName = it.locName,
                locType = it.locType,
                locTypeLabel = it.locTypeLabel,
                locUpdatedAt = it.locUpdatedAt,
                locUpdatedUsr = it.locUpdatedUsr
            )
        }

    fun mapLocationTypeResponseToDomain(input: LocationResponse) = LocationTypeModel(
        pR = input.pR,
        bD = input.bD,
        fL = input.fL,
        rO = input.rO
    )

    fun mapProjectCreatedResponseToDomain(input: ProjectCreatedItemResponse) = ProjectCreatedModel(
        locActive = input.locActive,
        locActiveLabel = input.locActiveLabel,
        locCode = input.locCode,
        locCreatedAt = input.locCreatedAt,
        locDispensation = input.locDispensation,
        locID = input.locID,
        locLatitude = input.locLatitude,
        locLongitude = input.locLongitude,
        locName = input.locName,
        locType = input.locType,
        locTypeLabel = input.locTypeLabel,
        locUpdatedAt = input.locUpdatedAt,
        locUpdatedUsr = input.locUpdatedUsr
    )

    fun mapBuildingCreatedResponseToDomain(input: BuildingCreatedItemResponse) = BuildingCreatedModel(
        locActive = input.locActive,
        locActiveLabel = input.locActiveLabel,
        locCode = input.locCode,
        locCreatedAt = input.locCreatedAt,
        locDispensation = input.locDispensation,
        locID = input.locID,
        locLatitude = input.locLatitude,
        locLongitude = input.locLongitude,
        locName = input.locName,
        locType = input.locType,
        locTypeLabel = input.locTypeLabel,
        locUpdatedAt = input.locUpdatedAt,
        locUpdatedUsr = input.locUpdatedUsr
    )
}