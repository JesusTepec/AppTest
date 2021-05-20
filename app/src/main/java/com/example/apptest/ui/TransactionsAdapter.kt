package com.example.apptest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.R
import model.Transaction


class TransactionsAdapter(private val usersList: ArrayList<Transaction>) :
    RecyclerView.Adapter<TransactionsAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtId: TextView = itemView.findViewById(R.id.txtId)
        val txtDesc: TextView = itemView.findViewById(R.id.txtDescription)
        val txtAmount: TextView = itemView.findViewById(R.id.txtAmount)
        val txtStatus: TextView = itemView.findViewById(R.id.txtStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = usersList[position]
        holder.txtId.text = item.id.toString()
        holder.txtDesc.text = item.description
        holder.txtAmount.text = item.amount
        holder.txtStatus.text = item.status
    }

    override fun getItemCount(): Int {
        return usersList.size
    }
}