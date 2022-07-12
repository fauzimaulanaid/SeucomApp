package com.fauzimaulana.seucomapp.view.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fauzimaulana.seucomapp.core.domain.model.ProjectCreatedModel
import com.fauzimaulana.seucomapp.core.domain.usecase.SeucomUseCase
import com.fauzimaulana.seucomapp.core.vo.Resource

class EditViewModel(private val seucomUseCase: SeucomUseCase): ViewModel() {
    fun updateData(dataID: String, locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double): LiveData<Resource<ProjectCreatedModel>> =
        seucomUseCase.updateData(dataID, locName, locType, locLat, locLon, locDis).asLiveData()
}