package su.afk.weathersky.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import su.afk.weathersky.data.location.DefaultLocationTracker
import su.afk.weathersky.data.repository.WeatherRepositoryImpl
import su.afk.weathersky.domain.location.LocationTracker
import su.afk.weathersky.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}