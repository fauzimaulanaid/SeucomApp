package com.fauzimaulana.seucomapp.core.data.source.remote

import android.util.Log
import com.fauzimaulana.seucomapp.core.data.source.remote.network.ApiService
import com.fauzimaulana.seucomapp.core.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    fun getLocationType(): Flow<ApiResponse<LocationResponse>> {
        return flow {
            try {
                val response = apiService.getType()
                if (response.status == "error") {
                    emit(ApiResponse.Error(response.message))
                    Log.e("RemoteDataSource: ", response.message)
                } else {
                    emit(ApiResponse.Success(response.data))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllProject(): Flow<ApiResponse<List<ProjectItem>>> {
        return flow {
            try {
                val response = apiService.getAllProject()
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun createProject(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double): Flow<ApiResponse<ProjectCreatedItemResponse>> {
        return flow {
            try {
                val response = apiService.createProject(locName, locType, locLat, locLon, locDis)
                if (response.status == "error") {
                    emit(ApiResponse.Error(response.message))
                    Log.e("RemoteDataSource: ", response.message)
                } else {
                    emit(ApiResponse.Success(response.data))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun createBuilding(locName: String, locType: String, locLat: Double, locLon: Double, locDis: Double, projectCode: String): Flow<ApiResponse<BuildingCreatedItemResponse>> {
        return flow {
            try {
                val response = apiService.createBuilding(locName, locType, locLat, locLon, locDis, projectCode)
                if (response.status == "error") {
                    emit(ApiResponse.Error(response.message))
                    Log.e("RemoteDataSource: ", response.message)
                } else {
                    emit(ApiResponse.Success(response.data))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}