package com.lumstep.contacts_2

interface FragmentNavigator {

    fun fromContactListToContact(position: Int)
    fun showRemoveDialog(contact: Contact) : Boolean
    fun closeContact()
}