package yalexaner.konturtest.ui.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
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

        model.requestErrorMessage.observe(viewLifecycleOwner) { message ->
            handleErrorMessage(message)
        }

        binding?.refresher?.setOnRefreshListener { model.requestContacts() }
    }

    private fun handleContactsLoaded(contacts: List<CachedContact>) {
        binding?.contacts?.adapter = ContactsListAdapter(
            contacts = contacts,
            onItemClick = ::onItemClick
        )

        showContent()
        hideRefresher()
    }

    private fun onItemClick(contact: CachedContact) {
        parentFragmentManager.commit {
            replace(R.id.main_fragment_container, ContactScreenFragment.withArgument(contact))
            addToBackStack(null)
        }
    }

    private fun handleErrorMessage(message: String) {
        hideRefresher()
        showSnackbar(message)
    }

    private fun showSnackbar(message: String) {
        val layout = binding?.root ?: return
        Snackbar.make(layout, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showContent() {
        binding?.progress?.visibility = View.GONE
        binding?.contacts?.visibility = View.VISIBLE
    }

    private fun hideRefresher() {
        binding?.refresher?.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}