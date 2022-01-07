package br.com.fornaro.chessclock.android.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val navigation: SettingsNavigation
) : ViewModel() {

    fun onBackButtonClicked() {
        navigation.back()
    }
}