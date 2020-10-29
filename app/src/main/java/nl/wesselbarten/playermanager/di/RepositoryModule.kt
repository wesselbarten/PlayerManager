package nl.wesselbarten.playermanager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import nl.wesselbarten.playermanager.data.repository.DefaultPlayerRepository
import nl.wesselbarten.playermanager.data.source.PlayersDataSource
import nl.wesselbarten.playermanager.domain.repository.PlayerRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePlayerRepository(playersDataSource: PlayersDataSource): PlayerRepository {
        return DefaultPlayerRepository(playersDataSource)
    }
}