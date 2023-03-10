package com.example.myapplication

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    //    lateinit var name:EditText
//    lateinit var id:EditText
//    lateinit var age: EditText
    //    private var db = FirebaseFirestore.getInstance()
    var count: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val save = findViewById<Button>(R.id.save)
        val name = findViewById<EditText>(R.id.PersonName)
        val id = findViewById<EditText>(R.id.PersonID)
        val age = findViewById<EditText>(R.id.PersonAge)
        val get = findViewById<Button>(R.id.get)
        val textDate = findViewById<TextView>(R.id.textView)
        val database = Firebase.database
        val myref = database.getReference()

        save.setOnClickListener {
            val name = name.text.toString();
            val id = id.text.toString();
            val age = age.text.toString();

            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )
            myref.child("message").child("$count").setValue(person)
            count++
            Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
        }
        get.setOnClickListener {
        // Read from the database
            myref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue()
                    textDate.text = value.toString()
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Failer", Toast.LENGTH_SHORT).show()
                }
            })
//    id 'kotlin-android-extensions'
        }
    }
}