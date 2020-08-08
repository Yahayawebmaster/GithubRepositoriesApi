package com.example.githubrepoapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepoapi.service.model.Project
import com.example.githubrepoapi.service.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ProjectListViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<List<Project>>> = MutableLiveData()

    fun getProjects() {
        viewModelScope.launch {
            try {
                val response = repository.getProjects()
                myResponse.value = response
            } catch (e: Exception) {
                Log.d("ProjectListViewModel", "Exception: " + e.message)
            }
        }
    }
}