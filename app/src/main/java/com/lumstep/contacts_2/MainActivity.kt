package com.lumstep.contacts_2

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), FragmentNavigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().run {
            val fragment = ContactsListFragment.newInstance()

            replace(R.id.frame_layout, fragment, ContactsListFragment.FRAGMENT_CONTACTS_LIST_TAG)

            commit()
        }
    }

    override fun fromContactListToContact(position: Int) {
        supportFragmentManager.beginTransaction().run {
            val fragment = ContactFragment.newInstance(position)

            replace(R.id.frame_layout, fragment, ContactFragment.FRAGMENT_CONTACT_TAG)

            addToBackStack(ContactFragment.FRAGMENT_CONTACT_TAG)
            commit()
        }
    }

    override fun showRemoveDialog(contact: Contact): Boolean {
        val alert = AlertDialog.Builder(this)
        alert.setTitle(R.string.remove_title)
        alert.setMessage(R.string.remove_message)
        alert.setCancelable(true)
        alert.setPositiveButton(
            "OK"
        ) { _, _ ->
            ContactListViewModel.removeContact(contact)
        }
        alert.setNegativeButton("No",null)
        alert.show()
        return true
    }

    override fun closeContact() {
        supportFragmentManager.popBackStack()
    }
}