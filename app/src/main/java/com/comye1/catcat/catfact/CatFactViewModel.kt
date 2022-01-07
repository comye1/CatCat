package com.comye1.catcat.catfact

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.comye1.catcat.catfact.database.CatFactItemLocal
import com.comye1.catcat.repository.Repository
import kotlinx.coroutines.launch

class CatFactViewModel(private val repository: Repository) : ViewModel() {

    val catFacts = MutableLiveData<List<CatFactItemLocal>>()

    fun getCatFacts() {
        viewModelScope.launch {
            val response = repository.fetchCatFacts()
            catFacts.value = response
            Log.d("network", "getCatFact")
        }
    }

    init {
        getCatFacts()
    }

}

class CatFactViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatFactViewModel(repository) as T
    }
}