package com.fauzimaulana.seucomapp.core.data.source

import com.fauzimaulana.seucomapp.core.data.source.remote.ApiResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.RemoteDataSource
import com.fauzimaulana.seucomapp.core.data.source.remote.response.*
import com.fauzimaulana.seucomapp.core.domain.model.*
import com.fauzimaulana.seucomapp.core.domain.reposirtory.ISeucomRepository
import com.fauzimaulana.seucomapp.core.utils.DataMapper
import com.fauzimaulana.seucomapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class SeucomRepository(private val remoteDataSource: RemoteDataSource): ISeucomRepository {
    override fun getAllProject(): Flow<Resource<List<ProjectModel>>> {
        return object : NetworkBoundResource<List<ProjectModel>, List<ProjectItem>>() {
            override fun createCall(): Flow<ApiResponse<List<ProjectItem>>> =
                remoteDataSource.getAllProject()

            override fun loadData(data: List<ProjectItem>): List<ProjectModel> =
                DataMapper.mapProjectResponseToDomain(data)
        }.asFlow()
    }

    override fun getAllBuildingByProject(projectCode: String): Flow<Resource<List<BuildingModel>>> {
        return object : NetworkBoundResource<List<BuildingModel>, List<BuildingItem>>() {
            override fun createCall(): Flow<ApiResponse<List<BuildingItem>>> =
                remoteDataSource.getAllBuildingByProject(projectCode)

            override fun loadData(data: List<BuildingItem>): List<BuildingModel> =
                DataMapper.mapBuildingResponseToDomain(data)

        }.asFlow()
    }

    override fun getAllFloorByBuilding(buildingCode: String): Flow<Resource<List<FloorModel>>> {
        return object : NetworkBoundResource<List<FloorModel>, List<FloorItem>>() {
            override fun createCall(): Flow<ApiResponse<List<FloorItem>>> =
                remoteDataSource.getAllFloorByBuilding(buildingCode)

            override fun loadData(data: List<FloorItem>): List<FloorModel> =
                DataMapper.mapFloorResponseToDomain(data)

        }.asFlow()
    }

    override fun getLocationType(): Flow<Resource<LocationTypeModel>> {
        return object : NetworkBoundResource<LocationTypeModel, LocationResponse>() {
            override fun createCall(): Flow<ApiResponse<LocationResponse>> =
                remoteDataSource.getLocationType()

            override fun loadData(data: LocationResponse): LocationTypeModel =
                DataMapper.mapLocationTypeResponseToDomain(data)

        }.asFlow()
    }

    override fun createProject(
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double
    ): Flow<Resource<ProjectCreatedModel>> {
        return object : NetworkBoundResource<ProjectCreatedModel, ProjectCreatedItemResponse>() {
            override fun createCall(): Flow<ApiResponse<ProjectCreatedItemResponse>> =
                remoteDataSource.createProject(locName, locType, locLat, locLon, locDis)

            override fun loadData(data: ProjectCreatedItemResponse): ProjectCreatedModel =
                DataMapper.mapProjectCreatedResponseToDomain(data)

        }.asFlow()
    }

    override fun createBuilding(
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double,
        projectCode: String
    ): Flow<Resource<BuildingCreatedModel>> {
        return object : NetworkBoundResource<BuildingCreatedModel, BuildingCreatedItemResponse>() {
            override fun createCall(): Flow<ApiResponse<BuildingCreatedItemResponse>> =
                remoteDataSource.createBuilding(locName, locType, locLat, locLon, locDis, projectCode)

            override fun loadData(data: BuildingCreatedItemResponse): BuildingCreatedModel =
                DataMapper.mapBuildingCreatedResponseToDomain(data)

        }.asFlow()
    }

    override fun createFloor(
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double,
        buildingCode: String
    ): Flow<Resource<FloorCreatedModel>> {
        return object : NetworkBoundResource<FloorCreatedModel, FloorCreatedItemResponse>() {
            override fun createCall(): Flow<ApiResponse<FloorCreatedItemResponse>> =
                remoteDataSource.createFloor(locName, locType, locLat, locLon, locDis, buildingCode)

            override fun loadData(data: FloorCreatedItemResponse): FloorCreatedModel =
                DataMapper.mapFloorCreatedResponseToDomain(data)

        }.asFlow()
    }

    override fun createRoom(
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double,
        floorCode: String
    ): Flow<Resource<RoomCreatedModel>> {
        return object : NetworkBoundResource<RoomCreatedModel, RoomCreatedItemResponse>() {
            override fun createCall(): Flow<ApiResponse<RoomCreatedItemResponse>> =
                remoteDataSource.createRoom(locName, locType, locLat, locLon, locDis, floorCode)

            override fun loadData(data: RoomCreatedItemResponse): RoomCreatedModel =
                DataMapper.mapRoomCreatedResponseToDomain(data)

        }.asFlow()
    }

}