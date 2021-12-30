package com.comye1.catcat.catpic

import android.util.Log
import androidx.lifecycle.*
import com.comye1.catcat.catpic.models.CatPic
import com.comye1.catcat.repository.Repository
import kotlinx.coroutines.launch

class CatPicViewModel(private val repository: Repository) : ViewModel() {

    val catPic = MutableLiveData<CatPic>()

    private fun getCatPic() {
        viewModelScope.launch {
            catPic.value = repository.getCatPic()
        }
    }

    fun refresh() {
        catPic.value = null // loading animation
        getCatPic()
    }

    init {
        getCatPic()
        Log.d("catpic", "init")
    }
}

class CatPicViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatPicViewModel(repository) as T
    }
}