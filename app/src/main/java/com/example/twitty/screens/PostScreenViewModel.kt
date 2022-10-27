package com.example.twitty.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewModelScope
import com.example.lib_data.data.repository.RepositoryImpl
import com.example.lib_data.domain.models.Comment
import com.example.lib_data.domain.models.Post
//import com.example.lib_data.domain.models.Token
import com.example.lib_data.domain.models.datastore.DataSource
import com.example.lib_data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 */
@HiltViewModel
class PostScreenViewModel @Inject constructor(
    private val repo: RepositoryImpl,
    private val store: DataSource
):ViewModel(){
    private val _comment: MutableStateFlow<Resource<List<Comment>>?> = MutableStateFlow(null)
    val comment = _comment.asStateFlow()

    fun getComment(id: Long){
       viewModelScope.launch {
           val token = store.getDataStore().first()
           _comment.value = repo.getComment("Bearer $token", id)
       }


    }
}