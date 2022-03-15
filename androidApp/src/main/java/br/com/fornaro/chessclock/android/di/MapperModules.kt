package br.com.fornaro.chessclock.android.di

import br.com.fornaro.chessclock.android.data.local.mappers.GameModeMapper
import br.com.fornaro.chessclock.android.data.local.mappers.GameModeMapperAlias
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModules {

    @Provides
    fun providesGameModeMapper(): GameModeMapperAlias = GameModeMapper
}
