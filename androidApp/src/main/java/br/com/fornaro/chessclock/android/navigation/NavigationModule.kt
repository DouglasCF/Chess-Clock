package br.com.fornaro.chessclock.android.navigation

import br.com.fornaro.chessclock.android.ui.home.HomeNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationScreenModule {

    @Binds
    abstract fun bindsHomeNavigation(impl: NavigationManager): HomeNavigation

}
