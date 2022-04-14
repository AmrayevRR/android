package com.example.todolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.*
import com.example.todolist.dao.CategoryDao
import com.example.todolist.model.ToDo

class ToDoListAdapter(val toDos: List<ToDo>, val context: Context): RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.to_do_item, parent, false)

        return ToDoViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return toDos.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDo = toDos.get(position)

        holder.titleTextView.text = toDo.title

        var db: AppDatabase = MyApplication.instance.getDatabase()!!
        var categoryDao: CategoryDao = db.categoryDao()

        val category = categoryDao.getCategory(toDo.category.toString())
        holder.categoryTextView.text = category.title

        if(toDo.status != "Undone") {
            holder.checkBox.isChecked = true
        }

        holder.id = toDo.id!!
    }

    class ToDoViewHolder(itemView: View, context: Context): RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        var categoryTextView: TextView = itemView.findViewById(R.id.category_text_view)
        var checkBox: CheckBox = itemView.findViewById(R.id.checkbox)

        var id = 0

        init {
            itemView.setOnClickListener {
                val action = ToDoListFragmentDirections.navigateToToDoDetailFragment(id)
                Navigation.findNavController(itemView).navigate(action)
            }
        }
    }
}