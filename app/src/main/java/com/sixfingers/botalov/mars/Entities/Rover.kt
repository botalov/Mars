package com.sixfingers.botalov.mars.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rover {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("landing_date")
    @Expose
    var landingDate: String? = null
    @SerializedName("launch_date")
    @Expose
    var launchDate: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("max_sol")
    @Expose
    var maxSol: Int? = null
    @SerializedName("max_date")
    @Expose
    var maxDate: String? = null
    @SerializedName("total_photos")
    @Expose
    var totalPhotos: Int? = null
    @SerializedName("cameras")
    @Expose
    var cameras: List<Camera_>? = null

}
