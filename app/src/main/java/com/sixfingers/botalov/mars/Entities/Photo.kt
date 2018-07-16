package com.sixfingers.botalov.mars.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("sol")
    @Expose
    var sol: Int? = null
    @SerializedName("camera")
    @Expose
    var camera: Camera? = null
    @SerializedName("img_src")
    @Expose
    var imgSrc: String? = null
    @SerializedName("earth_date")
    @Expose
    var earthDate: String? = null
    @SerializedName("rover")
    @Expose
    var rover: Rover? = null

}
