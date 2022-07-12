package com.fauzimaulana.seucomapp.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fauzimaulana.seucomapp.core.domain.model.ProjectModel
import com.fauzimaulana.seucomapp.core.domain.usecase.SeucomUseCase
import com.fauzimaulana.seucomapp.core.vo.Resource

class MainViewModel(private val seucomUseCase: SeucomUseCase): ViewModel() {
    fun getAllProject(): LiveData<Resource<List<ProjectModel>>> =
        seucomUseCase.getAllProject().asLiveData()
}