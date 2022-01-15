package com.comye1.catcat.catpic

import android.util.Log
import androidx.lifecycle.*
import com.comye1.catcat.catpic.models.CatPic
import com.comye1.catcat.repository.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class CatPicViewModel(private val repository: Repository) : ViewModel() {

    val catPic = MutableLiveData<CatPic>(null)

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("network", "caught $exception")
        catPic.value = CatPic(
            id = -1,
            webpurl = "https://www.popit.kr/wp-content/uploads/2018/09/xgiyhona_retry.png",
            url = "https://www.popit.kr/wp-content/uploads/2018/09/xgiyhona_retry.png",
            x = .0,
            y = .0
        )
    }

    private fun getCatPic() {
        viewModelScope.launch(handler) {
            catPic.value = repository.getCatPic()
            Log.d("network", "getCatPic")
        }
    }

    fun refresh() {
        catPic.value = null // loading animation
        getCatPic()
    }

//    init {
//        getCatPic()
//        Log.d("catpic", "init")
//    }
}

class CatPicViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatPicViewModel(repository) as T
    }
}