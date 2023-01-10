package xyz.siopa.retrofit_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.siopa.retrofit_app.repository.Repository

class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}