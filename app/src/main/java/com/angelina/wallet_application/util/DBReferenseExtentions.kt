package com.angelina.wallet_application.util

import com.angelina.wallet_application.model.User
import com.google.firebase.database.DatabaseReference

fun DatabaseReference.toUserCards(uid: String) = child("users").child(uid).child("cards")

fun DatabaseReference.toUserCard(uid: String, idCard: String) = toUserCards(uid).child(idCard)

fun DatabaseReference.toNoCards(uid: String) = child("users").child(uid).child("noCards")

fun DatabaseReference.toUsername(uid: String) = child("users").child(uid).child("username")

fun DatabaseReference.toAddUser(uid: String, username: String) =
    child("users").child(uid).setValue(User(username = username))

fun DatabaseReference.deleteUser(uid: String) =  child("users").child(uid).removeValue()

