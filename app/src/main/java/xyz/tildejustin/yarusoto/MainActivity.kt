package xyz.tildejustin.yarusoto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.accompanist.systemuicontroller.rememberSystemUiController
import xyz.tildejustin.yarusoto.ui.theme.YarusotoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YarusotoTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setNavigationBarColor(color = MaterialTheme.colorScheme.background)
                // doesn't work :p
                systemUiController.setStatusBarColor(color = MaterialTheme.colorScheme.background)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainPreview()
                }
            }
        }
    }
}


//@Preview(name = "Full Preview", showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPreview() {
    var todoList = remember { mutableStateListOf<TodoItem>() }
    var dialogState by remember { mutableStateOf(false) }
    var dialogText by remember { mutableStateOf("") }
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { dialogState = true },
                text = { Text(text = "Add") },
                icon = { Icon(Icons.Rounded.Add, null) },
            containerColor = MaterialTheme.colorScheme.primary
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = MaterialTheme.colorScheme.background,
    ) { padding ->
        var showChecked by remember { mutableStateOf(false) }
//        val todoList = if (!showChecked) {
//            todoDisplay.filter { todo -> !todo.checked }
//        } else {
//            todoDisplay
//        }
        Column(modifier = Modifier.padding(padding)) {
            Divider()
            Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Display completed to-dos?")
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
                todoList.forEachIndexed { index, todo ->
//                    val (checkState, setCheckState) = remember { mutableStateOf(todo.checked) }
                        Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = todo.checked, onCheckedChange = { todo.checked = it })
                            Text(text = todo.name)
                        }
                    SideEffect {
//                        todo.checked = checkState
                    }
                }
            }
        }
    }
    if (dialogState) {
        AlertDialog(
            onDismissRequest = { dialogState = false },
            title = {
                Text("Add a to-do")
            },
            text = {
                OutlinedTextField(
                    value = dialogText,
                    onValueChange = {
                        dialogText = it
                    },
                    label = {
                        Text("To-do")
                    }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        todoList.add(TodoItem(dialogText))
                        dialogText = ""
                        dialogState = false
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { dialogState = false }) {
                    Text("Cancel")
                    dialogText = ""
                }
            }
        )
    }
}



//@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(name = "Full Preview", showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun DefaultPreview() {
//    val todos = remember { mutableStateListOf(TodoItem("first"), TodoItem("second"), TodoItem("third", checked = true)) }
//    TodoListTheme {
//
//        Scaffold(
//            topBar = { SmallTopAppBar(title = { Text("To-dos") }) },
//            floatingActionButton = {
//                ExtendedFloatingActionButton(
//                    onClick = {},
//                    text = { Text(text = "Add To-do") },
//                    icon = {
//                        Icon(
//                            imageVector = Icons.Rounded.Create,
//                            null
//                        )
//                    },
//                containerColor = MaterialTheme.colorScheme.primary
//                )
//            },
//            floatingActionButtonPosition = FabPosition.End,
//            containerColor = MaterialTheme.colorScheme.background,
//            content = ({ TodoListDisplay(todos) })
//        )
//    }
//}
//
//@Composable
//fun TodoListDisplay(todos: List<TodoItem>) {
//    println(todos)
//    Column {
//        if (todos.isEmpty()) {
//            Text("No Todos! :partying_face:")
//        } else {
//            todos.forEach { todo ->
//                TodoDisplay(todo)
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TodoDisplay(todo: TodoItem) {
//    val checked = remember { mutableStateOf(todo.checked) }
//    Row {
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = {
//                checked.value = it
//            }
//        )
//        Text(
//            todo.name
//        )
////        Divider()
//        println("com.example.todo: " + todo.checked)
//    }
//}



