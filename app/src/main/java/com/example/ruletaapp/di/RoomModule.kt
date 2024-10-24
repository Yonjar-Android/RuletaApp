package com.example.ruletaapp.di

import android.content.Context
import androidx.room.Room
import com.example.ruletaapp.data.database.RouletteDatabase
import com.example.ruletaapp.data.database.dao.RouletteDao
import com.example.ruletaapp.data.database.dao.RouletteOptionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context):RouletteDatabase{
        return Room.databaseBuilder(
            context = context,
            klass = RouletteDatabase::class.java,
            name = "TasksDB"
        )
            .build()
    }

    @Provides
    @Singleton
    fun providesRouletteDao(database: RouletteDatabase):RouletteDao{
        return database.rouletteDao()
    }

    @Provides
    @Singleton
    fun providesRouletteOptionDao(database: RouletteDatabase):RouletteOptionDao{
        return database.rouletteOptionDao()
    }
}