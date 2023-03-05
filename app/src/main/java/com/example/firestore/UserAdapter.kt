package com.example.firestore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firestore.databinding.ItemUserBinding

class UserAdapter(val list: ArrayList<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(userItem: ItemUserBinding) : RecyclerView.ViewHolder(userItem.root){
        val binding = userItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imgProfilePicture.setImageResource(R.drawable.ic_person)
        holder.binding.tvUsername.text = list[position].name
        holder.binding.tvPhoneNumber.text = list[position].phoneNumber
        holder.binding.tvBirthdate.text = list[position].birthdate
    }

    override fun getItemCount(): Int {
        return list.size
    }

}