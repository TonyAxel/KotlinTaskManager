package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class SomethingAdd: AppCompatActivity() {
    val DBManager = DBManager(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Добавление"
        setContentView(R.layout.something_activity_add)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        DBManager.openDB();

        findViewById<Button>(R.id.button).setOnClickListener {
            val value_title = findViewById<TextInputEditText>(R.id.title_text_view_cart).text.toString()
            val value_desc = findViewById<TextInputEditText>(R.id.subtitle_text_view_cart).text.toString()
            if (value_title == ""){
                Toast.makeText(applicationContext,"Введите обязательные поля",Toast.LENGTH_LONG).show()
            }
            else{
                DBManager.insertToDB(value_title, value_desc);
                val intent = Intent(this@SomethingAdd, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DBManager.closeDB();
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}