package com.fauzimaulana.seucomapp.core.data.source.remote.network

import com.fauzimaulana.seucomapp.core.data.source.remote.response.BuildingCreatedResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.response.ListProjectResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.response.LocationTypeResponse
import com.fauzimaulana.seucomapp.core.data.source.remote.response.ProjectCreatedResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("locations/type")
    suspend fun getType(): LocationTypeResponse

    @GET("locations/project")
    suspend fun getAllProject(): ListProjectResponse

    @FormUrlEncoded
    @POST("locations")
    suspend fun createProject(
        @Field("locName") name: String,
        @Field("locType") type: String,
        @Field("locLatitude") lat: Double,
        @Field("locLongitude") lon: Double,
        @Field("locDispensation") dispensation: Double
    ): ProjectCreatedResponse

    @FormUrlEncoded
    @POST("locations")
    suspend fun createBuilding(
        @Field("locName") name: String,
        @Field("locType") type: String,
        @Field("locLatitude") lat: Double,
        @Field("locLongitude") lon: Double,
        @Field("locDispensation") dispensation: Double,
        @Field("projectCode") projectCode: String
    ): BuildingCreatedResponse
}