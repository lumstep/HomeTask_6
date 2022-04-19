package com.lumstep.contacts_2

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import java.text.FieldPosition

class ContactFragment : Fragment(R.layout.fragment_contact) {

    private var navigator: FragmentNavigator? = null

    private lateinit var viewModel: ContactViewModel
    private lateinit var photo: ImageView
    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText
    private lateinit var editTextPhoneNumber: EditText
    private lateinit var saveButton: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photo = view.findViewById(R.id.contact_image)
        editTextName = view.findViewById(R.id.editContactName)
        editTextSurname = view.findViewById(R.id.editContactSurname)
        editTextPhoneNumber = view.findViewById(R.id.editContactPhoneNumber)
        saveButton = view.findViewById(R.id.buttonSaveContact)

        photo.setBackgroundColor(viewModel.photoBackgroundColor)
        editTextName.setText(viewModel.name)
        editTextSurname.setText(viewModel.surname)
        editTextPhoneNumber.setText(viewModel.phoneNumber)

        saveButton.setOnClickListener {
            viewModel.saveContact(
                name = editTextName.text.toString(),
                surname = editTextSurname.text.toString(),
                phoneNumber = editTextPhoneNumber.text.toString()
            )
            navigator?.closeContact()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FragmentNavigator) navigator = context
        viewModel = ContactViewModel(requireArguments().getInt(POSITION))
    }

    companion object {
        const val FRAGMENT_CONTACT_TAG = "FRAGMENT_CONTACT_TAG"

        private const val POSITION = "POSITION"

        fun newInstance(position: Int) = ContactFragment().also {
            it.arguments = Bundle().apply {
                putInt(POSITION, position)
            }
        }
    }
}