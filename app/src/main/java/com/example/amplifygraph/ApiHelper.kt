package com.example.amplifygraph

import android.util.Log
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Todo

class ApiHelper {

    fun createTodo(
        todo: Todo,
        callback: (isCompleted: Boolean, id: String?) -> Unit
    ) {

        Amplify.API.mutate(
            ModelMutation.create(todo),
            {
                Log.i("ApiHelper", "Added Todo with id: ${it.data.id}")
                callback(true, it.data.id)
            },
            {
                Log.e("ApiHelper", "Create failed", it)
                callback(false, null)
            }
        )
    }

    fun getAllTodos(
        callback: (todosList: List<Todo>) -> Unit
    ) {
        Amplify.API.query(
            ModelQuery.list(Todo::class.java),
            { response ->
                callback(response.data.toList())
                Log.i("ApiHelper", "Todos received")
            },
            {
                callback(listOf())
                Log.e("MyAmplifyApp", "Query failure", it)
            }
        )
    }

    fun deleteTodo(
        todo: Todo,
        callback: (isCompleted: Boolean) -> Unit
    ) {
        Amplify.API.mutate(
            ModelMutation.delete(todo),
            {
                Log.i("ApiHelper", "Todo Deleted")
                callback(true)
            },
            {
                Log.e("ApiHelper", "Delete failed", it)
                callback(false)
            }
        )
    }

    fun updateTodo(
        todo: Todo,
        callback: (isCompleted: Boolean) -> Unit
    ) {
        Amplify.API.mutate(
            ModelMutation.update(
                todo,
                Todo.ID.eq(todo.id)
            ), {}, {}
        )
    }
}