package br.com.fornaro.chessclock.android.di

import br.com.fornaro.chessclock.usecases.GetFullScreenModeUseCase
import br.com.fornaro.chessclock.usecases.ToggleFullScreenModeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModules {

    @Provides
    fun providesToggleFullScreenModeUseCase() = ToggleFullScreenModeUseCase.create()

    @Provides
    fun providesGetFullScreenModeUseCase() = GetFullScreenModeUseCase.create()
}
