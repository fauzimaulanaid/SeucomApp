package com.fauzimaulana.seucomapp.core.domain.reposirtory

import com.fauzimaulana.seucomapp.core.data.source.remote.ApiResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.response.FloorItem
import com.fauzimaulana.seucomapp.core.data.source.remote.response.ProjectCreatedItemResponse
import com.fauzimaulana.seucomapp.core.domain.model.*
import com.fauzimaulana.seucomapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface ISeucomRepository {
    fun getAllProject(): Flow<Resource<List<ProjectModel>>>

    fun getAllBuildingByProject(projectCode: String): Flow<Resource<List<BuildingModel>>>

    fun getAllFloorByBuilding(buildingCode: String): Flow<Resource<List<FloorModel>>>

    fun getLocationType(): Flow<Resource<LocationTypeModel>>

    fun createProject(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double): Flow<Resource<ProjectCreatedModel>>

    fun createBuilding(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double, projectCode: String): Flow<Resource<BuildingCreatedModel>>

    fun createFloor(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double, buildingCode: String): Flow<Resource<FloorCreatedModel>>

    fun createRoom(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double, floorCode: String): Flow<Resource<RoomCreatedModel>>
}