package com.angelina.wallet_application.util

import com.angelina.wallet_application.entity.firebase.CardFirebase
import com.angelina.wallet_application.entity.room.CardEntity
import com.angelina.wallet_application.model.Card

fun CardFirebase.toCardEntity() =
    CardEntity(
        0,
        idCard,
        idShop,
        cardNumber,
        countOfClicks
    )

fun List<CardEntity>.toListCard() = map {
    Card(
        it.idCard,
        it.idShop,
        it.number,
        it.countOfClicks
    )
}

fun Card.toCardEntity() =
    CardEntity(
        idCard + 1,
        idCard,
        idShop,
        cardNumber,
        countOfClicks
    )

fun CardEntity.toCard() =
    Card(
        idCard,
        idShop,
        number,
        countOfClicks
    )

fun Card.toCardFirebase() =
    CardFirebase(
        idCard,
        idShop,
        cardNumber,
        countOfClicks
    )