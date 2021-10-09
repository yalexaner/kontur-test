package yalexaner.konturtest.ui.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import yalexaner.konturtest.databinding.FragmentMainScreenBinding
import yalexaner.konturtest.network.models.Contact
import yalexaner.konturtest.ui.components.contactslist.MyItemRecyclerViewAdapter

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
            handleContactsLoaded(contacts)
        }
    }

    private fun handleContactsLoaded(contacts: List<Contact>) {
        binding?.contacts?.adapter = MyItemRecyclerViewAdapter(contacts)
        binding?.progress?.visibility = View.GONE
        binding?.contacts?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}