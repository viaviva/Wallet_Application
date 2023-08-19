package com.angelina.wallet_application.util

import com.angelina.wallet_application.entity.firebase.CardFirebase
import com.angelina.wallet_application.entity.room.CardEntity
import com.angelina.wallet_application.model.Card

fun CardFirebase.toCardEntity() =
    CardEntity(
        idCard,
        idShop,
        cardNumber,
        countOfClicks
    )

fun List<CardEntity>.toListCard() = map {
    Card(
        it.id,
        it.idShop,
        it.number,
        it.countOfClicks
    )
}

fun Card.toCardEntity() =
    CardEntity(
        idCard,
        idShop,
        cardNumber,
        countOfClicks
    )

fun CardEntity.toCard() =
    Card(
        id,
        idShop,
        number,
        countOfClicks
    )