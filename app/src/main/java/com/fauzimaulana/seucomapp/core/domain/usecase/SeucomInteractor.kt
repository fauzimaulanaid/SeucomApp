package com.fauzimaulana.seucomapp.core.domain.usecase

import com.fauzimaulana.seucomapp.core.domain.model.*
import com.fauzimaulana.seucomapp.core.domain.reposirtory.ISeucomRepository
import com.fauzimaulana.seucomapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class SeucomInteractor(private val seucomRepository: ISeucomRepository): SeucomUseCase {
    override fun getAllProject(): Flow<Resource<List<ProjectModel>>> =
        seucomRepository.getAllProject()

    override fun getLocationType(): Flow<Resource<LocationTypeModel>> =
        seucomRepository.getLocationType()

    override fun getAllBuildingByProject(projectCode: String): Flow<Resource<List<BuildingModel>>> =
        seucomRepository.getAllBuildingByProject(projectCode)

    override fun getAllFloorByBuilding(buildingCode: String): Flow<Resource<List<FloorModel>>> =
        seucomRepository.getAllFloorByBuilding(buildingCode)

    override fun createProject(
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double
    ): Flow<Resource<ProjectCreatedModel>> =
        seucomRepository.createProject(locName, locType, locLat, locLon, locDis)

    override fun updateData(
        dataID: String,
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double
    ): Flow<Resource<ProjectCreatedModel>> =
        seucomRepository.updateData(dataID, locName, locType, locLat, locLon, locDis)

    override fun createBuilding(
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double,
        projectCode: String
    ): Flow<Resource<BuildingCreatedModel>> =
        seucomRepository.createBuilding(locName, locType, locLat, locLon, locDis, projectCode)

    override fun createFloor(
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double,
        buildingCode: String
    ): Flow<Resource<FloorCreatedModel>> =
        seucomRepository.createFloor(locName, locType, locLat, locLon, locDis, buildingCode)

    override fun createRoom(
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double,
        floorCode: String
    ): Flow<Resource<RoomCreatedModel>> =
        seucomRepository.createRoom(locName, locType, locLat, locLon, locDis, floorCode)
}