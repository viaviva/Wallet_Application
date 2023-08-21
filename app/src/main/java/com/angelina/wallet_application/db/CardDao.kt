package com.angelina.wallet_application.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.angelina.wallet_application.entity.room.CardEntity

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity)

    @Update
    suspend fun updateCard(card: CardEntity)

    @Delete
    suspend fun deleteNote(card: CardEntity)

    @Query("SELECT * FROM card")
    suspend fun getAllCards(): List<CardEntity>

    @Query("SELECT * FROM card WHERE idCard = :id")
    suspend fun getCard(id: String): CardEntity

    @Query("SELECT * FROM card ORDER BY idCard DESC LIMIT 1")
    suspend fun getCardMaxId(): CardEntity

}