package com.fauzimaulana.seucomapp.core.data.source.remote.network

import com.fauzimaulana.seucomapp.core.data.source.remote.response.*
import retrofit2.http.*

interface ApiService {
    @GET("locations/type")
    suspend fun getType(): LocationTypeResponse

    @GET("locations/project")
    suspend fun getAllProject(): ListProjectResponse

    @GET("locations/building/{projectCode}")
    suspend fun getBuildingByProject(@Path("projectCode") projectCode: String): ListBuildingResponse

    @GET("locations/floor/{buildingCode}")
    suspend fun getFloorByBuilding(@Path("buildingCode") buildingCode: String): ListFloorResponse

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

    @FormUrlEncoded
    @POST("locations")
    suspend fun createFloor(
        @Field("locName") name: String,
        @Field("locType") type: String,
        @Field("locLatitude") lat: Double,
        @Field("locLongitude") lon: Double,
        @Field("locDispensation") dispensation: Double,
        @Field("buildingCode") buildingCode: String
    ): FloorCreatedResponse

    @FormUrlEncoded
    @POST("locations")
    suspend fun createRoom(
        @Field("locName") name: String,
        @Field("locType") type: String,
        @Field("locLatitude") lat: Double,
        @Field("locLongitude") lon: Double,
        @Field("locDispensation") dispensation: Double,
        @Field("floorCode") floorCode: String
    ): RoomCreatedResponse
}