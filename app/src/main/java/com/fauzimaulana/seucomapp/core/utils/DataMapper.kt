package com.fauzimaulana.seucomapp.core.utils

import com.fauzimaulana.seucomapp.core.data.source.remote.response.*
import com.fauzimaulana.seucomapp.core.domain.model.*

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

    fun mapBuildingResponseToDomain(input: List<BuildingItem>): List<BuildingModel> =
        input.map {
            BuildingModel(
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

    fun mapFloorResponseToDomain(input: List<FloorItem>): List<FloorModel> =
        input.map {
            FloorModel(
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

    fun mapFloorCreatedResponseToDomain(input: FloorCreatedItemResponse) = FloorCreatedModel(
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

    fun mapRoomCreatedResponseToDomain(input: RoomCreatedItemResponse) = RoomCreatedModel(
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