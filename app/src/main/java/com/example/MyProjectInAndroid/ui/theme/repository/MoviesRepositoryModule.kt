package com.example.MyProjectInAndroid.ui.theme.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesRepositoryModule {
    @Binds
    abstract fun bindMoviesRepository(repository: MoviesDBRepositoryImpl): MoviesDBRepository
    @Binds
    abstract fun bindFirebaseRepository(repository: FirebaseRepositoryImpl): FirebaseRepository
}