package app.olauncher.ui



import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import app.olauncher.R

class NotionActivity : AppCompatActivity() {

    private lateinit var todoListView: ListView
    private lateinit var newTodoEditText: EditText
    private lateinit var addTodoButton: Button

    private lateinit var todoAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notion)

        todoListView = findViewById(R.id.todoList)
        newTodoEditText = findViewById(R.id.newTodoEditText)
        addTodoButton = findViewById(R.id.addTodoButton)

        todoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        todoListView.adapter = todoAdapter

        // Fetch todos from API (you will implement this)
        fetchTodos()

        // Add new todo button click listener
        addTodoButton.setOnClickListener {
            val newTodo = newTodoEditText.text.toString().trim()
            if (newTodo.isNotEmpty()) {
                // Call API to add new todo (you will implement this)
                addTodo(newTodo)
            }
        }

        // Set up item click listener to handle finishing todo (you will implement this)
        todoListView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = todoAdapter.getItem(position)
            if (selectedItem != null) {
                // Call API to mark todo as finished (you will implement this)
                finishTodo(selectedItem)
            }
        }
    }

    private fun fetchTodos() {
        // Implement API call to fetch todos here
        // Use Retrofit, Volley, or other HTTP client library
        // Update todoAdapter with fetched todos
        val todos = listOf("Todo 1", "Todo 2", "Todo 3") // Example data, replace with API call
        todoAdapter.clear()
        todoAdapter.addAll(todos)
    }

    private fun addTodo(todo: String) {
        // Implement API call to add new todo here
        // After successful addition, fetch updated todos and update todoAdapter
        fetchTodos()
        newTodoEditText.text.clear()
    }

    private fun finishTodo(todo: String) {
        // Implement API call to mark todo as finished here
        // After successful marking, fetch updated todos and update todoAdapter
        fetchTodos()
    }
}
