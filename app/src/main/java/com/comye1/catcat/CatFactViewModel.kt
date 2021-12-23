package com.comye1.catcat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.comye1.catcat.models.CatFactItem
import com.comye1.catcat.repository.Repository
import kotlinx.coroutines.launch

class CatFactViewModel(private val repository: Repository) : ViewModel() {

    val catFacts = MutableLiveData<List<CatFactItem>>()

    fun getCatFacts() {
        viewModelScope.launch {
            val response = repository.getCatFacts()
            catFacts.value = response
        }
    }

    init {
        getCatFacts()
    }

}

class CatViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatFactViewModel(repository) as T
    }
}