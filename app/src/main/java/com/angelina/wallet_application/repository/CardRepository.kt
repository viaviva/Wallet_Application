package com.angelina.wallet_application.repository

import android.util.Log
import com.angelina.wallet_application.db.CardDao
import com.angelina.wallet_application.entity.firebase.CardFirebase
import com.angelina.wallet_application.model.Card
import com.angelina.wallet_application.util.toCard
import com.angelina.wallet_application.util.toCardEntity
import com.angelina.wallet_application.util.toCardFirebase
import com.angelina.wallet_application.util.toListCard
import com.angelina.wallet_application.util.toNoCards
import com.angelina.wallet_application.util.toUserCard
import com.angelina.wallet_application.util.toUserCards
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
        Log.e("SP", sharedPreferenceRepository.getUserId())
        database.toUserCards(sharedPreferenceRepository.getUserId()).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val list = arrayListOf<CardFirebase>()

                if (p0.exists()) {
                    for (i in p0.children) {
                        val itm = i.getValue(CardFirebase::class.java)
                        list.add(itm!!)
                    }

                    GlobalScope.launch {
                        listOfCards.emit(list)
                        Log.e("LIST OF CARDS", listOfCards.value.toString())
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
        listOfCards.value.forEach {
            GlobalScope.async {
                cardDao.insertCard(it.toCardEntity())
            }.onAwait
        }
    }

    suspend fun getAllCards() = cardDao.getAllCards().toListCard()

    suspend fun getMaxId() = cardDao.getCardMaxId().idCard

    suspend fun getCard(id: String) = cardDao.getCard(id).toCard()

    suspend fun addCard(card: Card) {
        cardDao.insertCard(card.toCardEntity())
        database.toUserCard(sharedPreferenceRepository.getUserId(), card.idCard.toString()).setValue(card.toCardFirebase())
    }

    suspend fun deleteCard(id: Long) {
        val card = getCard(id.toString())

        cardDao.deleteNote(card.toCardEntity())
        database.toUserCard(sharedPreferenceRepository.getUserId(), id.toString()).removeValue()
    }

    fun setNoCards() =
        database.toNoCards(sharedPreferenceRepository.getUserId()).setValue(false)

    suspend fun deleteAllCards() = cardDao.deleteAllCards()

}