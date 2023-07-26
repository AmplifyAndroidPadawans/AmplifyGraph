package com.example.amplifygraph

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.Todo

class TodosAdapter(private val todosList: List<Todo>, private val listener: TodosInterface) : RecyclerView.Adapter<TodosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todosList[position]
        holder.id.text = item.id
        holder.title.text = item.name
        holder.message.text = item.description
        holder.llTodo.setOnClickListener {
            listener.todoSelected(item)
        }
    }

    override fun getItemCount(): Int {
        return todosList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val llTodo: LinearLayout = itemView.findViewById(R.id.ll_todo)
        val id: TextView = itemView.findViewById(R.id.todo_id)
        val title: TextView = itemView.findViewById(R.id.todo_title)
        val message: TextView = itemView.findViewById(R.id.todo_message)
    }
}

interface TodosInterface {
    fun todoSelected(todo: Todo)
}