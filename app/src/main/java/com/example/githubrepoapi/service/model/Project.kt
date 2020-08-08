package com.example.githubrepoapi.service.model

import com.google.gson.annotations.SerializedName

data class Project(
    val name: String,
    @SerializedName("language")
    val languages: String,
    val watchers: Int,
    @SerializedName("open_issues")
    val openIssues: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("clone_url")
    val cloneUrl: String
)