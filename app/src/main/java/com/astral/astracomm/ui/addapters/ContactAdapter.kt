class ContactsAdapter(
    private val contacts: List<User>
) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvContactName.text = contacts[position].nickname
    }

    override fun getItemCount() = contacts.size
}