package com.example.githubrepoapi.service.repository

import com.example.githubrepoapi.api.RetrofitInstance
import com.example.githubrepoapi.service.model.Project
import retrofit2.Response

class Repository {

    suspend fun getProjects(): Response<List<Project>> = RetrofitInstance.api.getProjects()
}