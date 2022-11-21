package com.messieyawo.ispaceroomcoroutineflowproject.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.messieyawo.ispaceroomcoroutineflowproject.data.models.Genres
import com.messieyawo.ispaceroomcoroutineflowproject.data.repository.musicRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeFragmentViewModel(
    application: Application,
   private val repository: musicRepository
):AndroidViewModel(application) {

    private val _genres: MutableLiveData<Response<Genres>> = MutableLiveData()
    val genres: LiveData<Response<Genres>>
        get() = _genres

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage


    fun getAllCategoryList() = viewModelScope.launch {
        Log.i("TAG", "Query with page")
        _errorMessage.value = null
        _isLoading.value = true
        try {
            if(hasInternetConnection()) {

                val fetchedAllGenres = repository.getAllKindsOfMusic()
                Log.i("TAGVIEWMODEL", "Got posts: ${fetchedAllGenres.body()?.data.toString()}")
              //  val currentCategories = _categories.value

                _genres.value =  fetchedAllGenres

            } else {

                _errorMessage.value = true.toString()
            }

        } catch (e: Exception) {
            _errorMessage.value = e.message
            Log.e("TAG", "Exception $e")
        } finally {
            _isLoading.value = false
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }


}