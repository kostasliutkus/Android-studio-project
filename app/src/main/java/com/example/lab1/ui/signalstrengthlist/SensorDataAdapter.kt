package com.example.lab1.ui.signalstrengthlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.R
import com.example.lab1.data.User

class SensorDataAdapter(private var sensorList: List<User>) :
    RecyclerView.Adapter<SensorDataAdapter.SensorDataViewHolder>() {

    class SensorDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.textView)
        val matTextView: TextView = itemView.findViewById(R.id.textView2)
        val sensTextView: TextView = itemView.findViewById(R.id.textView3)
        val stiprumasTextView: TextView = itemView.findViewById(R.id.textView4)
        val button: Button = itemView.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sensor_data_layout, parent, false)
        return SensorDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SensorDataViewHolder, position: Int) {
        val sensorData = sensorList[position]
        holder.idTextView.text = sensorData.id.toString()
        holder.matTextView.text = sensorData.mac.toString()
        holder.sensTextView.text = sensorData.sensorius.toString()
        holder.stiprumasTextView.text = sensorData.stiprumas.toString()
    }

    override fun getItemCount(): Int {
        return sensorList.size
    }
    fun setUsers(users: List<User>) {
        sensorList = users
        notifyItemInserted(users.lastIndex)
        notifyDataSetChanged()
    }
    fun updateData(newList: List<User>) {
        sensorList = newList
        notifyDataSetChanged()
    }
}