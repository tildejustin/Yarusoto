package xyz.tildejustin.yarusoto

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoBox(todo: TodoItem) {
    val (checkState, setCheckState) = remember { mutableStateOf(todo.checked) }
    Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checkState, onCheckedChange = { setCheckState(it) })
        Text(text = todo.name)
    }
    SideEffect {
        todo.checked = checkState
    }
}