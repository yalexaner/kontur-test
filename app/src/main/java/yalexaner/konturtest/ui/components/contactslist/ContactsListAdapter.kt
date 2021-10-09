package yalexaner.konturtest.ui.components.contactslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yalexaner.konturtest.databinding.ItemContactBinding
import yalexaner.konturtest.network.models.Contact

class MyItemRecyclerViewAdapter(
    private val contacts: List<Contact>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemContactBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.contact = contacts[position]
    }

    override fun getItemCount(): Int = contacts.size

    inner class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)
}