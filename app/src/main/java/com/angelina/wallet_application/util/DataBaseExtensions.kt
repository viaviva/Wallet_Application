package com.angelina.wallet_application.util

import android.content.Context
import androidx.room.Room
import com.angelina.wallet_application.db.AppDataBase

fun Room.buildDB(context: Context) = databaseBuilder(context, AppDataBase::class.java, "database")
    .allowMainThreadQueries()
    .build()