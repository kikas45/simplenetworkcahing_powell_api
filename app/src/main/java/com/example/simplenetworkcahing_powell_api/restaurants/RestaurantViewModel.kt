package com.example.simplenetworkcahing_powell_api.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.simplenetworkcahing_powell_api.api.RestaurantApi
import com.example.simplenetworkcahing_powell_api.data.Restaurant
import com.example.simplenetworkcahing_powell_api.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


// we made changes, by not talking to the api but instead to our repository


@HiltViewModel
class RestaurantViewModel @Inject constructor(
    repository: RestaurantRepository,
) : ViewModel() {

    val restaurants = repository.getRestaurants().asLiveData()
}


// by  using asLiveData , the corountines and every mutiple things than once
// flow is more flexibale than live data but not as easy to use with viewmodel and UI


//    private val resturantsLiveData = MutableLiveData<List<Restaurant>>()
//    val restaurant:LiveData<List<Restaurant>> = resturantsLiveData
//
//    private val  error= MutableLiveData<String>()
//
//    fun getError(): LiveData<String> = error
//
//    init {
//
//        viewModelScope.launch {
//            try {
//                val restaurants = api.getRestaurants()
//                delay(2000) // don't do this in rel app
//                resturantsLiveData.postValue(restaurants)
//            }catch (e:Exception){
//
//                error.value = e.message.toString()
//            }
//
//        }
//
//    }

