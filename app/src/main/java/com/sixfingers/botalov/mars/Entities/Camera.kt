package com.sixfingers.botalov.mars.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Camera {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("rover_id")
    @Expose
    var roverId: Int? = null
    @SerializedName("full_name")
    @Expose
    var fullName: String? = null
}
