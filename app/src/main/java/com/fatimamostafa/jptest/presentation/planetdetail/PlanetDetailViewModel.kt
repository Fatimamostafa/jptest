package com.fatimamostafa.jptest.presentation.planetdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    val planetName: String = savedStateHandle.get<String>(PLANET_NAME_SAVED_STATE_KEY)!!

    companion object {
        private const val PLANET_NAME_SAVED_STATE_KEY = "planetName"
    }
}
