package su.afk.weathersky.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import su.afk.weathersky.data.location.DefaultLocationTracker
import su.afk.weathersky.domain.location.LocationTracker
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationService(defaultLocationTracker: DefaultLocationTracker): LocationTracker
}