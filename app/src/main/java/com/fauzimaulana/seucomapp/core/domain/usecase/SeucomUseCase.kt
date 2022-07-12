package com.fauzimaulana.seucomapp.core.domain.usecase

import com.fauzimaulana.seucomapp.core.domain.model.BuildingCreatedModel
import com.fauzimaulana.seucomapp.core.domain.model.LocationTypeModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectCreatedModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectModel
import com.fauzimaulana.seucomapp.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface SeucomUseCase {
    fun getAllProject(): Flow<Resource<List<ProjectModel>>>

    fun getLocationType(): Flow<Resource<LocationTypeModel>>

    fun createProject(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double): Flow<Resource<ProjectCreatedModel>>

    fun createBuilding(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double, projectCode: String): Flow<Resource<BuildingCreatedModel>>
}