package br.com.fornaro.chessclock.android.navigation

import br.com.fornaro.chessclock.android.ui.home.HomeNavigation
import br.com.fornaro.chessclock.android.ui.settings.SettingsNavigation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    fun providesNavigation() = NavigationManager
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationScreenModule {

    @Binds
    abstract fun bindsSetupNavigation(impl: NavigationManager): SetupNavigation

    @Binds
    abstract fun bindsHomeNavigation(impl: NavigationManager): HomeNavigation

    @Binds
    abstract fun bindsSettingsNavigation(impl: NavigationManager): SettingsNavigation

}
