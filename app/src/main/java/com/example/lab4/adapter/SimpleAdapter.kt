package com.example.lab4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.R
import com.example.lab4.model.SomethingData

class SimpleAdapter(
    val handleTap: (SomethingData) -> Unit
): RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    var items: List<SomethingData> = listOf()

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(item: SomethingData){
            itemView.apply {
                if(item.title.length > 15){
                    findViewById<TextView>(R.id.title_text_view).text = item.title.subSequence(0,15).toString() + "..."
                }
                else{
                    findViewById<TextView>(R.id.title_text_view).text = item.title
                }
                if(item.description.length > 70){
                    findViewById<TextView>(R.id.subtitle_text_view).text = item.description.subSequence(0,70).toString() + "..."
                }else{
                    findViewById<TextView>(R.id.subtitle_text_view).text = item.description
                }

                setOnClickListener{
                    handleTap(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun set(items: List<SomethingData>){
        this.items = listOf()
        this.items = items
        notifyDataSetChanged()
    }

}