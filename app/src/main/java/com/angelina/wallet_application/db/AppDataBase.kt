package com.angelina.wallet_application.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.angelina.wallet_application.entity.room.CardEntity

@Database(
    entities = [CardEntity::class],
    version = 1
)

abstract class AppDataBase : RoomDatabase() {

    abstract fun getCardDao(): CardDao

}