package com.example.todolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.adapter.ToDoListAdapter
import com.example.todolist.dao.CategoryDao
import com.example.todolist.dao.ToDoDao
import com.example.todolist.model.Category
import com.example.todolist.model.ToDo

/**
 * A simple [Fragment] subclass.
 * Use the [ToDoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToDoListFragment : Fragment() {

    lateinit var db: AppDatabase
    lateinit var categoryDao: CategoryDao
    lateinit var toDoDao: ToDoDao
    lateinit var categories: List<Category>
    lateinit var toDos: List<ToDo>

    lateinit var addButton: Button
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_to_do_list, container, false)

        initDatabase()

        fetchCategories()

        fetchToDos()

        initUI(view)

        setUI(view)

        initRecyclerView()

        return view
    }

    private fun initUI(view: View) {
        addButton = view.findViewById(R.id.add_button)
        recyclerView = view.findViewById(R.id.recycler_view)
    }

    private  fun setUI(view: View) {
        addButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToAddFragment)
        }
    }

    private fun initDatabase() {
        db = MyApplication.instance.getDatabase()!!
        categoryDao = db.categoryDao()
        toDoDao = db.toDoDao()
    }

    private fun fetchCategories() {
        categories = categoryDao.getAll()
        for (category in categories) {
            Log.d("Category", category.id.toString() + " " + category.title.toString())
        }
    }

    private fun fetchToDos() {
        toDos = toDoDao.getAll()
        for (toDo in toDos) {
            Log.d("Category", toDo.id.toString() + " " + toDo.title.toString())
        }
    }

    private fun initRecyclerView() {
        val toDoListAdapter = ToDoListAdapter(toDos, requireContext())
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = toDoListAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ToDoListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ToDoListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}