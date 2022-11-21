package com.messieyawo.ispaceroomcoroutineflowproject.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.messieyawo.ispaceroomcoroutineflowproject.data.repository.musicRepository

class HomeFragmentViewModelProvider(
    val app: Application,
    private val repository: musicRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(app, repository) as T
    }
}