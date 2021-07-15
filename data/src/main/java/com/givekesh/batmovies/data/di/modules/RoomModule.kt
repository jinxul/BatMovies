package com.givekesh.batmovies.data.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.givekesh.batmovies.data.source.local.MainDatabase
import com.givekesh.batmovies.data.source.local.MovieDetailsDao
import com.givekesh.batmovies.data.source.local.MoviesDao
import com.givekesh.batmovies.data.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideDatabaseBuilder(
        @ApplicationContext context: Context
    ): RoomDatabase.Builder<MainDatabase> = Room.databaseBuilder(
        context,
        MainDatabase::class.java,
        Constant.DATABASE_NAME
    )

    @Singleton
    @Provides
    fun provideMainDatabase(
        databaseBuilder: RoomDatabase.Builder<MainDatabase>
    ): MainDatabase = databaseBuilder.fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideMoviesDao(db: MainDatabase): MoviesDao = db.moviesDao()

    @Singleton
    @Provides
    fun provideMovieDetailsDao(db: MainDatabase): MovieDetailsDao = db.movieDetailsDao()
}