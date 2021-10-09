package yalexaner.konturtest.ui.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import yalexaner.konturtest.R
import yalexaner.konturtest.databinding.FragmentMainScreenBinding
import yalexaner.konturtest.db.CachedContact
import yalexaner.konturtest.ui.components.contactslist.ContactsListAdapter
import yalexaner.konturtest.ui.screens.contact.ContactScreenFragment

/**
 * Начальный экран приложение, который содержит список всех контактов
 */
@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private var binding: FragmentMainScreenBinding? = null

    private val model: MainScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.contacts.observe(viewLifecycleOwner) { contacts ->
            if (contacts.isNotEmpty()) {
                handleContactsLoaded(contacts)
            }
        }
    }

    private fun handleContactsLoaded(contacts: List<CachedContact>) {
        binding?.contacts?.adapter = ContactsListAdapter(
            contacts = contacts,
            onItemClick = ::onItemClick
        )

        binding?.progress?.visibility = View.GONE
        binding?.contacts?.visibility = View.VISIBLE
    }

    private fun onItemClick(contact: CachedContact) {
        parentFragmentManager.commit {
            replace(R.id.main_fragment_container, ContactScreenFragment.withArgument(contact))
            addToBackStack(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}