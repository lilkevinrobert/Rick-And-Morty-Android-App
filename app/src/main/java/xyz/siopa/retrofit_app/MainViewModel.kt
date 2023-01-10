package xyz.siopa.retrofit_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.siopa.retrofit_app.model.Post
import xyz.siopa.retrofit_app.repository.Repository

class MainViewModel(private val repository: Repository): ViewModel() {

    // In the view model we lunch the kotlin coroutines
    val myResponse: MutableLiveData<Post> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
}