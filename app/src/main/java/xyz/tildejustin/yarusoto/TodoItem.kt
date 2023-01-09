package xyz.tildejustin.yarusoto

import java.util.*

data class TodoItem(var name: String, var due: Date? = null, var checked: Boolean = false)