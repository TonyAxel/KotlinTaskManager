package com.example.lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.adapter.SimpleAdapter
import com.example.lab4.model.SomethingData

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        SimpleAdapter{
            routeToSomethingActivity(it)
        }
    }

    val DBManager = DBManager(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recycleView).apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
        DBManager.openDB();
        val simpleItems = DBManager.readDbData();
        adapter.set(simpleItems)

        findViewById<Button>(R.id.button2).setOnClickListener {
            val intent = Intent(this@MainActivity, SomethingAdd::class.java)
            startActivity(intent)
        }

    }

    private fun routeToSomethingActivity(model: SomethingData) {
        val intent = Intent(this, SomethingActivity::class.java).apply {
            putExtra(SomethingActivity.SOMETHING_MODEL, model)
        }
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        DBManager.closeDB();
    }

}