package com.fauzimaulana.seucomapp.core.data.source

import com.fauzimaulana.seucomapp.core.data.source.remote.ApiResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.RemoteDataSource
import com.fauzimaulana.seucomapp.core.data.source.remote.response.BuildingCreatedItemResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.response.LocationResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.response.ProjectCreatedItemResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.response.ProjectItem
import com.fauzimaulana.seucomapp.core.domain.model.BuildingCreatedModel
import com.fauzimaulana.seucomapp.core.domain.model.LocationTypeModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectCreatedModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectModel
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

}