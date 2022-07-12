package com.fauzimaulana.seucomapp.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fauzimaulana.seucomapp.core.domain.model.BuildingModel
import com.fauzimaulana.seucomapp.core.domain.model.FloorModel
import com.fauzimaulana.seucomapp.core.domain.usecase.SeucomUseCase
import com.fauzimaulana.seucomapp.core.vo.Resource

class DetailViewModel(private val seucomUseCase: SeucomUseCase): ViewModel() {
    fun getAllBuildingByProject(projectCode: String): LiveData<Resource<List<BuildingModel>>> =
        seucomUseCase.getAllBuildingByProject(projectCode).asLiveData()

    fun getAllFloorByBuilding(buildingCode: String): LiveData<Resource<List<FloorModel>>> =
        seucomUseCase.getAllFloorByBuilding(buildingCode).asLiveData()
}