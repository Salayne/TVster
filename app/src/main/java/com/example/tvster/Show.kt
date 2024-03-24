package com.example.tvster

import com.google.gson.annotations.SerializedName

class Show {
    @JvmField
    @SerializedName("name")
    var title: String? = null

   /* @JvmField
    @SerializedName("overview")
    var overview: String? = null*/

    @JvmField
    @SerializedName("poster_path")
    var  posterUrl: String? = null

    @JvmField
    @SerializedName("first_air_date")
    var  airDate: String? = null

}