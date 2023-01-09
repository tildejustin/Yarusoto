package xyz.tildejustin.yarusoto

import java.util.*


class TodoApp() {
    val todos: MutableList<TodoItem> = mutableListOf()

    fun addTodo(name: String, due: Date?) {
        todos.add(TodoItem(name, due))
    }

    fun onCreate() {
        var app = TodoApp()
    }

    fun main() {
        println("running")
        todos.add(TodoItem("test task"))
    }
}