package com.comye1.catcat.catfact

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.comye1.catcat.catfact.database.CatFactItemLocal
import com.comye1.catcat.repository.Repository
import kotlinx.coroutines.launch

class CatFactViewModel(private val repository: Repository) : ViewModel() {

    val catFacts = MutableLiveData<List<CatFactItemLocal>>()

    private fun getCatFacts() {
        viewModelScope.launch {
            try {
                val response = repository.fetchCatFacts()
                catFacts.value = response
                Log.d("network", "getCatFact")
            }catch (e: Error) {
                Log.d("network", "getCatFact ${e.message}")
            }
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