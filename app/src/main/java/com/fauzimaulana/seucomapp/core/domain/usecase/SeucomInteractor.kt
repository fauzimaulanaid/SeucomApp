package com.fauzimaulana.seucomapp.core.domain.usecase

import com.fauzimaulana.seucomapp.core.domain.model.BuildingCreatedModel
import com.fauzimaulana.seucomapp.core.domain.model.LocationTypeModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectCreatedModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectModel
import com.fauzimaulana.seucomapp.core.domain.reposirtory.ISeucomRepository
import com.fauzimaulana.seucomapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class SeucomInteractor(private val seucomRepository: ISeucomRepository): SeucomUseCase {
    override fun getAllProject(): Flow<Resource<List<ProjectModel>>> =
        seucomRepository.getAllProject()

    override fun getLocationType(): Flow<Resource<LocationTypeModel>> =
        seucomRepository.getLocationType()

    override fun createProject(
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double
    ): Flow<Resource<ProjectCreatedModel>> =
        seucomRepository.createProject(locName, locType, locLat, locLon, locDis)

    override fun createBuilding(
        locName: String,
        locType: String,
        locLat: Double,
        locLon: Double,
        locDis: Double,
        projectCode: String
    ): Flow<Resource<BuildingCreatedModel>> =
        seucomRepository.createBuilding(locName, locType, locLat, locLon, locDis, projectCode)
}