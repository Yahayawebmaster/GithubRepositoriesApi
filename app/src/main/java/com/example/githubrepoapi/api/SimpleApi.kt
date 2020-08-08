package com.example.githubrepoapi.api

import com.example.githubrepoapi.service.model.Project
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("/users/antoniokranjcina/repos")
    suspend fun getProjects(): Response<List<Project>>
}