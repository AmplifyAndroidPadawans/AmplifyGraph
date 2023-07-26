package com.example.amplifygraph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Todo

class MainActivity : AppCompatActivity(), TodosInterface {
    private lateinit var recyclerView: RecyclerView

    private val api by lazy {
        ApiHelper()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        api.getAllTodos { todosList ->
            runOnUiThread {
                val adapter = TodosAdapter(todosList, this)
                recyclerView.adapter = adapter
            }
        }

        /*val todo = Todo.builder()
            .name("My first task")
            .description("This is a first description")
            .build()

        ApiHelper().createTodo(todo) { isCompleted, id ->
            runOnUiThread {
                if (isCompleted) {
                    id?.let {
                        Toast.makeText(this, "Registered ID $it", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }*/
    }

    override fun todoSelected(todo: Todo) {
        api.deleteTodo(todo) { _ ->
            api.getAllTodos { todosList ->
                runOnUiThread {
                    val adapter = TodosAdapter(todosList, this)
                    recyclerView.adapter = adapter

                    Toast.makeText(this, "Todo with id ${todo.id} has been deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

