package com.lumstep.contacts_2

class ContactViewModel(private val position: Int) {


    var photoBackgroundColor: Int
        get() = ContactListViewModel.contactsListFullVersion[position].photoBackgroundColor
        set(value) {
            ContactListViewModel.contactsListFullVersion[position].photoBackgroundColor = value
        }

    var name: String
        get() = ContactListViewModel.contactsListFullVersion[position].name
        set(value) {
            ContactListViewModel.contactsListFullVersion[position].name = value
        }
    var surname: String
        get() = ContactListViewModel.contactsListFullVersion[position].surname
        set(value) {
            ContactListViewModel.contactsListFullVersion[position].surname = value
        }
    var phoneNumber: String
        get() = ContactListViewModel.contactsListFullVersion[position].phoneNumber
        set(value) {
            ContactListViewModel.contactsListFullVersion[position].phoneNumber = value
        }

    fun saveContact(name: String, surname: String, phoneNumber: String) {
        this.name = name
        this.surname = surname
        this.phoneNumber = phoneNumber
    }
}