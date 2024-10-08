package com.example.sqlitelogoutapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        val welcometextview = findViewById<TextView>(R.id.welcometextview)
        val logout = findViewById<Button>(R.id.logout)
        logout.setOnClickListener {
            // Clear user session or any relevant data
            clearUserSession()

            // Optionally, you can navigate back to the login screen
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // Optional: Close the current activity
        }
    }

    private fun clearUserSession() {
        // Implement session clearing logic here, e.g., delete user data from SharedPreferences or database
        val sharedPrefs = getSharedPreferences("UserSession", MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.clear() // Clear all data
        editor.apply() // Save changes
    }
}