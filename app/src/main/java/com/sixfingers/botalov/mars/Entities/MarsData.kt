package com.sixfingers.botalov.mars.Entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MarsData {

    @SerializedName("photos")
    @Expose
    var photos: List<Photo>? = null

}
