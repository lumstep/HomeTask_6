package com.lumstep.contacts_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lumstep.contacts_2.databinding.ContactItemBinding

class ContactAdapter(private val navigator: FragmentNavigator?) :
    RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    class ContactHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ContactItemBinding.bind(item)
        fun bind(contact: Contact, navigator: FragmentNavigator?) {
            with(binding) {
                contactCardView.setOnClickListener {
                    navigator?.fromContactListToContact(
                        ContactListViewModel.contactsListFullVersion.indexOf(
                            contact
                        )
                    )
                }
                contactCardView.setOnLongClickListener {
                    navigator?.showRemoveDialog(contact)!!
                }
                photoBackgroundColor.setBackgroundColor(contact.photoBackgroundColor)
                contactName.text = contact.name
                contactSurname.text = contact.surname
                contactPhone.text = contact.phoneNumber
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(ContactListViewModel.contactsList[position], navigator)
    }

    override fun getItemCount(): Int {
        return ContactListViewModel.contactsList.size
    }
}