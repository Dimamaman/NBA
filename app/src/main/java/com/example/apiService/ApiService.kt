package com.example.apiService

import com.example.data.MainClass
import com.example.data.PlayersList
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("players")
    suspend fun getAllPlayers(): Response<PlayersList>
}