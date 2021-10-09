package yalexaner.konturtest.ui.components.contactslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yalexaner.konturtest.databinding.ItemContactBinding
import yalexaner.konturtest.db.CachedContact

class ContactsListAdapter(
    private val contacts: List<CachedContact>,
    private val onItemClick: ((CachedContact) -> Unit)? = null
) : RecyclerView.Adapter<ContactsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemContactBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contacts[position]
        holder.binding.apply {
            contact = item
            root.setOnClickListener { onItemClick?.invoke(item) }
        }
    }

    override fun getItemCount(): Int = contacts.size

    inner class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)
}