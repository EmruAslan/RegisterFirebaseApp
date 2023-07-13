package com.example.registerfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.registerfirebase.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            val firstname = binding.editTextTextPersonName.text.toString()
            val lastname = binding.editTextTextPersonName2.text.toString()
            val age = binding.editTextTextPersonName3.text.toString()
            val username = binding.editText4.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(firstname, lastname, age, username)
            database.child(username).setValue(User).addOnSuccessListener {

                binding.editTextTextPersonName.text.clear()
                binding.editTextTextPersonName2.text.clear()
                binding.editTextTextPersonName3.text.clear()
                binding.editText4.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }


        }
    }
}