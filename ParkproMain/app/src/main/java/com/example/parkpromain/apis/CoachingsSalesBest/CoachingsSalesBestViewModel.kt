package com.example.parkpromain.apis.CoachingsSalesBest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class CoachingsSalesBestViewModel(application: Application) : AndroidViewModel(application) {

    private var coachingsSalesBestRepository: CoachingsSalesBestRepository
    private var coachingsSalesBestResponseLiveData: LiveData<CoachingsSalesBestResponse>

    init {
        coachingsSalesBestRepository = CoachingsSalesBestRepository()
        coachingsSalesBestResponseLiveData = coachingsSalesBestRepository.getCoachingsSalesBestResponseLiveData()
    }

    fun coachingsSalesBest(){
        coachingsSalesBestRepository.coachingsSalesBest()
    }

    fun getCoachingsSalesBestResponseLiveData(): LiveData<CoachingsSalesBestResponse> {
        return coachingsSalesBestResponseLiveData
    }
}