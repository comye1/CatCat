package com.comye1.catcat.catbreed

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.comye1.catcat.catbreed.models.BreedImageResponseItem
import com.comye1.catcat.catbreed.models.BreedItem
import com.comye1.catcat.repository.Repository
import kotlinx.coroutines.launch

class CatBreedViewModel(private val repository: Repository) : ViewModel() {

//    lateinit var catBreeds: List<BreedItem>

    /*
    initialized 안된다는 에러 - 잘 되다가 왜이러는 걸까
     */
    var catBreeds = MutableLiveData<List<BreedItem>>()
    var catImages = MutableLiveData<List<BreedImageResponseItem>>()

    private fun getCatBreeds() {
        viewModelScope.launch {
            val response = repository.getCatBreeds()
            catBreeds.value = response
            Log.d("network", "getCatBreeds")
        }
    }

    fun getCatBreedItem(id: String): BreedItem? =
        catBreeds.value?.find { it.id == id }

    /*
    id로 Breed 이미지 가져오기 - navigate시 호출
     */
    fun getCatImagesById(id: String) {
        catImages.value = null
        viewModelScope.launch {
            val response = repository.getCatImagesByBreed(id)
            Log.d("network", "id : $id")
            catImages.value = response
            Log.d("network", "getCatImagesById")
        }
    }

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