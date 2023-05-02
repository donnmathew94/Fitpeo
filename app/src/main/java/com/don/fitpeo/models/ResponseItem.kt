package com.don.fitpeo.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseItem (

    @SerializedName("albumId"      ) var albumId      : Int?    = null,
    @SerializedName("id"           ) var id           : Int?    = null,
    @SerializedName("title"        ) var title        : String? = null,
    @SerializedName("url"          ) var url          : String? = null,
    @SerializedName("thumbnailUrl" ) var thumbnailUrl : String? = null

) : Parcelable
