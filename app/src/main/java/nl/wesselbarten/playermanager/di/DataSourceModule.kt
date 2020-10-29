package nl.wesselbarten.playermanager.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import nl.wesselbarten.playermanager.data.source.PlayersDataSource
import nl.wesselbarten.playermanager.data.source.players.CsvPlayersDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providePlayersDataSource(@ApplicationContext context: Context): PlayersDataSource {
        return CsvPlayersDataSource(context)
    }
}