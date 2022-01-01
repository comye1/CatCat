package com.comye1.catcat.catbreed

import androidx.lifecycle.*
import com.comye1.catcat.catbreed.models.BreedItem
import com.comye1.catcat.repository.Repository
import kotlinx.coroutines.launch

class CatBreedViewModel(private val repository: Repository) : ViewModel() {

    lateinit var catBreeds: List<BreedItem>

    private fun getCatBreeds() {
        viewModelScope.launch {
            val response = repository.getCatBreeds()
            catBreeds = response
        }
    }

    fun getCatBreedItem(id: String): BreedItem? =
        catBreeds.find { it.id == id }


    init {
        getCatBreeds()
    }
}

class CatBreedViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatBreedViewModel(repository) as T
    }
}