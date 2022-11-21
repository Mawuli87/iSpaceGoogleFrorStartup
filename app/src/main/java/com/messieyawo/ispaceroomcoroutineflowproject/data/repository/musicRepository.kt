package com.messieyawo.ispaceroomcoroutineflowproject.data.repository

import com.messieyawo.ispaceroomcoroutineflowproject.data.api.RetrofitInstance
import com.messieyawo.ispaceroomcoroutineflowproject.data.models.Genres
import retrofit2.Response

class musicRepository {

    //live data response start
    suspend fun getAllKindsOfMusic(): Response<Genres> {
        return RetrofitInstance.api.fetchAllGenres()
    }


}