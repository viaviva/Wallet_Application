package com.angelina.wallet_application.repository

import android.util.Log
import com.angelina.wallet_application.db.CardDao
import com.angelina.wallet_application.entity.firebase.CardFirebase
import com.angelina.wallet_application.model.Card
import com.angelina.wallet_application.util.toCard
import com.angelina.wallet_application.util.toCardEntity
import com.angelina.wallet_application.util.toCardFirebase
import com.angelina.wallet_application.util.toListCard
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
class CardRepository @Inject constructor(
    private val database: DatabaseReference,
    private val cardDao: CardDao,
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    val listOfCards = MutableStateFlow(arrayListOf<CardFirebase>())

    fun getCardsFromFirebase() {
        database.child("users").child(sharedPreferenceRepository.getUserId()).child("cards")
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(p0: DataSnapshot) {
                    val list = arrayListOf<CardFirebase>()
                    Log.e("LIST", list.toString())
                    Log.e("LIST", p0.toString())

                    if (p0.exists()) {
                        for (i in p0.children) {
                            val itm = i.getValue(CardFirebase::class.java)
                            list.add(itm!!)
                        }
                        Log.e("LIST", "Data fetched successfully")
                        Log.e("NEW", list.toString())

                        GlobalScope.launch {
                            Log.e("LIST 2", listOfCards.value.toString())
                            listOfCards.emit(list)
                            Log.e("DATA", listOfCards.value.toString())
                            putCardsIntoRoom()
                        }
                    } else {
                        Log.e("NEW", "Unknown error occurred")
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })

    }

    suspend fun putCardsIntoRoom() {
        Log.e("ROOM", listOfCards.value.toString())

        listOfCards.value.forEach {
            GlobalScope.async {
                Log.e("LIST OF CARDS", it.toString())
                cardDao.insertCard(it.toCardEntity())
                Log.e("LIST OF CARDS DONE", it.toString())
            }.onAwait
        }
    }

    suspend fun getAllCards() = cardDao.getAllCards().toListCard()

    suspend fun getMaxId() = cardDao.getCardMaxId().idCard

    suspend fun getCard(id: String) = cardDao.getCard(id).toCard()

    suspend fun addCard(card: Card) {
        cardDao.insertCard(card.toCardEntity())

        database.child("users").child(sharedPreferenceRepository.getUserId()).child("cards")
            .child(card.idCard.toString()).setValue(card.toCardFirebase())
    }

    suspend fun deleteCard(id: Long) {
        val card = getCard(id.toString())

        cardDao.deleteNote(card.toCardEntity())
        database.child("users").child(sharedPreferenceRepository.getUserId()).child("cards")
            .child(id.toString()).removeValue()
    }

}