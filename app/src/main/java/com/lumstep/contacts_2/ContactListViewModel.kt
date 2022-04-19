package com.lumstep.contacts_2

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.stream.Collectors
import kotlin.random.Random

class ContactListViewModel {
    companion object {
        var recyclerView: RecyclerView? = null
        var contactsList = ArrayList<Contact>() // список который отображается пользователю
        var contactsListFullVersion = ArrayList<Contact>() // изначальный список со всеми контактами

        fun initContacts() {
            for (id in 0..500) {
                contactsList.add(
                    Contact(
                        id,
                        randomName(),
                        randomSurname(),
                        randomPhoneNumber(),
                        randomColor(),
                    )
                )
            }
            contactsListFullVersion = contactsList.clone() as ArrayList<Contact>
        }

        private fun randomName(): String {
            return listOf(
                "Александр",
                "Максим",
                "Михаил",
                "Марк",
                "Иван",
                "Артём",
                "Лев",
                "Дмитрий",
                "Матвей",
                "Даниил"
            ).random()

        }

        private fun randomSurname(): String {
            return listOf(
                "Смирнов",
                "Иванов",
                "Кузнецов",
                "Соколов",
                "Попов",
                "Лебедев",
                "Козлов",
                "Новиков",
                "Морозов",
                "Петров"
            ).random()
        }

        private fun randomPhoneNumber(): String {
            val firstPart = "+375"
            val secondPart = listOf("44", "29", "17", "33").random()
            val thirdPart = (1000000..9999999).random()
            return firstPart + secondPart + thirdPart.toString()
        }

        private fun randomColor(): Int {
            return Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
        }

        fun findContact(query: String) {
            if (query.isEmpty()) clearFilterText()
            val newContactsList = contactsListFullVersion.stream()
                .filter { a -> a.name == query || a.surname == query }
                .collect(Collectors.toList()) as ArrayList<Contact>
            updateChanges(newContactsList)
        }

        fun setFilterText(newText: String) {
            val newContactsList = contactsListFullVersion.stream()
                .filter { a -> newText in a.name || newText in a.surname }
                .collect(Collectors.toList()) as ArrayList<Contact>
            updateChanges(newContactsList)
        }

        private fun clearFilterText() {
            updateChanges(contactsListFullVersion)
        }

        private fun updateChanges(newContactList: ArrayList<Contact>) {
            val diffUtil = ContactDiffCallback(contactsList, newContactList)
            val diffResult = DiffUtil.calculateDiff(diffUtil)
            recyclerView!!.adapter.let {
                if (it != null) {
                    diffResult.dispatchUpdatesTo(it)
                }
            }
            contactsList = newContactList
        }

        @SuppressLint("NotifyDataSetChanged")
        fun removeContact(contact:Contact) {
            contactsListFullVersion.remove(contact)
            contactsList.remove(contact)
            recyclerView!!.adapter!!.notifyDataSetChanged()
        }
    }
}