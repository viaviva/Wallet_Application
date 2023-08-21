package com.angelina.wallet_application.entity.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class CardEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "idCard")
    val idCard: Long,

    @ColumnInfo(name = "idShop")
    val idShop: Long,

    @ColumnInfo(name = "number")
    val number: String,

    @ColumnInfo(name = "countOfClicks")
    val countOfClicks: Long,

)