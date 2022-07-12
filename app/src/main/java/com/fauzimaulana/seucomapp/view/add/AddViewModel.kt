package com.fauzimaulana.seucomapp.view.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fauzimaulana.seucomapp.core.domain.model.BuildingCreatedModel
import com.fauzimaulana.seucomapp.core.domain.model.LocationTypeModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectCreatedModel
import com.fauzimaulana.seucomapp.core.domain.model.ProjectModel
import com.fauzimaulana.seucomapp.core.domain.usecase.SeucomUseCase
import com.fauzimaulana.seucomapp.core.vo.Resource

class AddViewModel(private val seucomUseCase: SeucomUseCase): ViewModel() {
    fun getLocationType(): LiveData<Resource<LocationTypeModel>> =
        seucomUseCase.getLocationType().asLiveData()

    fun createProject(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double): LiveData<Resource<ProjectCreatedModel>> =
        seucomUseCase.createProject(locName, locType, locLat, locLon, locDis).asLiveData()

    fun getAllProject(): LiveData<Resource<List<ProjectModel>>> =
        seucomUseCase.getAllProject().asLiveData()

    fun createBuilding(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double, projectCode: String): LiveData<Resource<BuildingCreatedModel>> =
        seucomUseCase.createBuilding(locName, locType, locLat, locLon, locDis, projectCode).asLiveData()
}