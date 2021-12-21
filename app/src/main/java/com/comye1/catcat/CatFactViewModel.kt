//package com.comye1.catcat
//
//import android.util.Log
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.comye1.catcat.models.CatFact
//import com.comye1.catcat.models.CatFactItem
//import com.comye1.catcat.network.CatFactApi
//import kotlinx.coroutines.launch
//
//class CatFactViewModel : ViewModel() {
//
//    private val _catFacts = MutableLiveData<List<CatFactItem>>()
//
//    val catFacts: LiveData<List<CatFactItem>>
//        get() = _catFacts
//
//
////    private val _result = MutableLiveData<String>()
////    val result: LiveData<String>
////        get() = _result
//
//    val result = mutableStateOf(CatFact())
//
//    private fun getCatFacts() {
//        viewModelScope.launch {
//            try {
//                val listResult = CatFactApi.retrofitService.getCatFacts()
//                result.value = listResult
//                Log.d("result" , " : ${listResult.size}")
//
//            } catch (e: Exception) {
////                result.value = "Failure : ${e.message}"
//            }
//        }
//    }
//
//    init {
//        getCatFacts()
//    }
//
//}