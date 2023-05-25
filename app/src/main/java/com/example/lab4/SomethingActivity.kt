package com.example.lab4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.model.SomethingData

class SomethingActivity: AppCompatActivity() {

    companion object{
        const val SOMETHING_MODEL = "sgm"
    }
    val DBManager = DBManager(this);

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        DBManager.openDB();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.something_activity)
        title = "Подробнее"
        val model = intent.getSerializableExtra(SOMETHING_MODEL) as? SomethingData
        findViewById<TextView>(R.id.title_text_view_cart).text = model?.title
        findViewById<TextView>(R.id.subtitle_text_view_cart).text = model?.description
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<Button>(R.id.buttonDelete).setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Поздравляем. Задача выполнена")
            DBManager.deleteInBD(model?.id.toString())
            builder.setPositiveButton("OK"){dialogInterface, which ->
                val intent = Intent(this, MainActivity::class.java )
                startActivity(intent)
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
