package com.angelina.wallet_application.db

import android.content.Context
import androidx.room.Room
import com.angelina.wallet_application.util.buildDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideNoteDao(@ApplicationContext context: Context): CardDao {
        return Room.buildDB(context).getCardDao()
    }

}