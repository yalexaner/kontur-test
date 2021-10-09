package yalexaner.konturtest.ui.screens.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import yalexaner.konturtest.databinding.FragmentContactScreenBinding
import yalexaner.konturtest.db.CachedContact

class ContactScreenFragment : Fragment() {

    private var contact: CachedContact? = null

    private var binding: FragmentContactScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contact = arguments?.getParcelable(KEY_CONTACT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.contact = contact
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
        contact = null
    }

    companion object {

        fun withArgument(contact: CachedContact): ContactScreenFragment {
            return ContactScreenFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_CONTACT, contact)
                }
            }
        }

        private const val KEY_CONTACT = "Contact Argument"
    }
}