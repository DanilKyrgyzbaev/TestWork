package com.mobilemirdev.testwork.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobilemirdev.testwork.databinding.ItemRecyclerViewBinding
import com.mobilemirdev.testwork.model.User

class Adapter : RecyclerView.Adapter<MainViewHolder>() {

    var users = mutableListOf<User>()

    fun setUserList(users: List<User>) {
        this.users = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemRecyclerViewBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = users[position]
        holder.binding.title.text = user.title
        holder.binding.description.text = user.body

    }

    override fun getItemCount(): Int {
        return users.size
    }
}

class MainViewHolder(val binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {

}