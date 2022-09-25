package com.example.data

import com.google.gson.annotations.SerializedName

data class PlayersList(
    @SerializedName("data")
    val players: List<Player>
)