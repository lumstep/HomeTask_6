package com.lumstep.contacts_2

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lumstep.contacts_2.databinding.FragmentContactsListBinding


class ContactsListFragment : Fragment(R.layout.fragment_contacts_list) {

    private var navigator: FragmentNavigator? = null
    private lateinit var binding: FragmentContactsListBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FragmentNavigator) navigator = context

        ContactListViewModel.initContacts()
        binding = FragmentContactsListBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val searchView: SearchView = view.findViewById(R.id.search_view)

        val contactAdapter = ContactAdapter(navigator)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = contactAdapter
        ContactListViewModel.recyclerView = recyclerView

        searchView.setOnClickListener {
            searchView.isIconified = false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    ContactListViewModel.findContact(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    ContactListViewModel.setFilterText(newText)
                }
                return true
            }

        }
        )
    }

    companion object {
        const val FRAGMENT_CONTACTS_LIST_TAG = "FRAGMENT_CONTACTS_LIST_TAG"
        fun newInstance() = ContactsListFragment()
    }
}