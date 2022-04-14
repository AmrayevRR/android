package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.todolist.dao.CategoryDao
import com.example.todolist.dao.ToDoDao
import com.example.todolist.model.Category
import com.example.todolist.model.ToDo


/**
 * A simple [Fragment] subclass.
 * Use the [ToDoDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToDoDetailFragment : Fragment() {

    val args: ToDoDetailFragmentArgs by navArgs()

    lateinit var db: AppDatabase
    lateinit var toDoDao: ToDoDao
    lateinit var categoryDao: CategoryDao

    lateinit var titleTextView: TextView
    lateinit var descriptionTextView: TextView
    lateinit var statusTextView: TextView
    lateinit var categoryTextView: TextView
    lateinit var durationTextView: TextView

    lateinit var toDo: ToDo

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
        val view =  inflater.inflate(R.layout.fragment_to_do_detail, container, false)

        initDatabase()

        getToDo()

        initUI(view)

        setUI()

        return view
    }

    private fun initDatabase() {
        db = MyApplication.instance.getDatabase()!!
        toDoDao = db.toDoDao()
        categoryDao = db.categoryDao()
    }

    private fun getToDo() {
        val id = args.toDoId
        toDo = toDoDao.getToDo(id.toString())
    }

    private fun initUI(view: View) {
        titleTextView = view.findViewById(R.id.to_do_title_text_view)
        descriptionTextView = view.findViewById(R.id.to_do_description_text_view)
        statusTextView = view.findViewById(R.id.to_do_status_text_view)
        categoryTextView = view.findViewById(R.id.to_do_category_text_view)
        durationTextView = view.findViewById(R.id.to_do_duration_text_view)
    }

    private fun setUI() {
        titleTextView.text = toDo.title
        descriptionTextView.text = toDo.description
        statusTextView.text =  toDo.status

        val category: Category = categoryDao.getCategory(toDo.category.toString())

        categoryTextView.text = category.title
        durationTextView.text = "${toDo.duration.days} days, ${toDo.duration.hours} hours"
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ToDoDetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}