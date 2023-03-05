package com.example.firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firestore.databinding.ActivityAddUserBinding
import com.example.firestore.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore

        binding.btnAddUser.setOnClickListener {
            val name = binding.etUsername.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val birthdate = binding.etBirthdate.text.toString()

            if(name.isNotEmpty() && phoneNumber.isNotEmpty() && birthdate.isNotEmpty()){
                val user = User(name, phoneNumber, birthdate)
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this, "${user.name} is Added Successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
                    }
                finish()
            }else{
                Toast.makeText(this, "Check your data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}