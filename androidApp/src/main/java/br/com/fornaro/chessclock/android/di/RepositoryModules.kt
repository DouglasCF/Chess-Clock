package br.com.fornaro.chessclock.android.di

import br.com.fornaro.chessclock.repositories.GameModeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModules {

    @Provides
    @Singleton
    fun providesGameModeRepository() = GameModeRepository()
}
