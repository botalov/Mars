package com.sixfingers.botalov.mars.Network

import com.sixfingers.botalov.mars.Entities.MarsData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


interface INetwork {
    @GET("curiosity/photos")
    fun getCuriosityPhotos(@Query("api_key") api_key: String, @Query("earth_date")  sol: String): Observable<MarsData>

    @GET("opportunity/photos")
    fun getOpportunityPhotos(@Query("api_key") api_key: String, @Query("earth_date")  sol: String): Observable<MarsData>
}