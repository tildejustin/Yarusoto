package xyz.tildejustin.yarusoto

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun todoList(todos: List<TodoItem>, padding: PaddingValues): List<TodoItem> {
    var showChecked by remember { mutableStateOf(false) }
    val todoList = if (!showChecked) {
        todos.filter { todo -> !todo.checked }
    } else {
        todos
    }
    Column(modifier = Modifier.padding(padding)) {
        Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Show checked boxes?")
            Checkbox(
                checked = showChecked,
                onCheckedChange = {
                    showChecked = it
                })
        }
        Divider()
        if (todoList.isEmpty()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                Text("No To-dos!", fontSize = 20.sp)
                Text(
                    "\uD83E\uDD73",
                    textAlign = TextAlign.Center,
                    fontSize = 100.sp,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                )
            }
        } else {
            todoList.forEach { todo -> TodoBox(todo) }
        }
    }
    return todos
}
//        todos.forEach { todo -> TodoBox(todo) } ?: Text("No Todos! :partying_face:")
