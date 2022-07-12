package com.fauzimaulana.seucomapp.view.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fauzimaulana.seucomapp.core.domain.model.*
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

    fun getAllBuildingByProject(projectCode: String): LiveData<Resource<List<BuildingModel>>> =
        seucomUseCase.getAllBuildingByProject(projectCode).asLiveData()

    fun createFloor(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double, buildingCode: String): LiveData<Resource<FloorCreatedModel>> =
        seucomUseCase.createFloor(locName, locType, locLat, locLon, locDis, buildingCode).asLiveData()

    fun getAllFloorByBuilding(buildingCode: String): LiveData<Resource<List<FloorModel>>> =
        seucomUseCase.getAllFloorByBuilding(buildingCode).asLiveData()

    fun createRoom(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double, floorCode: String): LiveData<Resource<RoomCreatedModel>> =
        seucomUseCase.createRoom(locName, locType, locLat, locLon, locDis, floorCode).asLiveData()
}