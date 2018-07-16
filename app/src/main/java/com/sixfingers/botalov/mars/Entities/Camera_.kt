package com.sixfingers.botalov.mars.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Camera_ {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("full_name")
    @Expose
    var fullName: String? = null
}
