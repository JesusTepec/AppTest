package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.R



class TransactionsAdapter(private val usersList: ArrayList<Any>) :
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
    }

    override fun getItemCount(): Int {
        return usersList.size
    }
}