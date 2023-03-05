package com.example.firestore

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestore.databinding.ActivityMainBinding
import com.example.firestore.databinding.DialogAddUserBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Firebase.firestore

        getAllUsers()

        binding.btnAddUser.setOnClickListener {
            startActivity(Intent(this, AddUserActivity::class.java))
        }
    }

    fun getAllUsers() {
        val userList = ArrayList<User>()
        db.collection("users")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val user = User(
                        document.getString("name")!!,
                        document.getString("phoneNumber")!!,
                        document.get("birthdate").toString()
                    )
                    userList.add(user)
                }
                binding.rvUsers.layoutManager = LinearLayoutManager(this)
                binding.rvUsers.adapter = UserAdapter(userList)
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
            }
    }
}