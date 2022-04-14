package com.example.todolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.example.todolist.adapter.CategoryListAdapter
import com.example.todolist.dao.CategoryDao
import com.example.todolist.dao.ToDoDao
import com.example.todolist.model.Category
import com.example.todolist.model.ToDo
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours


/**
 * A simple [Fragment] subclass.
 * Use the [AddToDoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddToDoFragment : Fragment() {

    lateinit var titleEditText: EditText
    lateinit var descriptionEditText: EditText
    lateinit var autoCompleteTextView: AutoCompleteTextView
    lateinit var daysPlusTextView: TextView
    lateinit var daysMinusTextView: TextView
    lateinit var daysAmountTextView: TextView
    lateinit var hoursPlusTextView: TextView
    lateinit var hoursMinusTextView: TextView
    lateinit var hoursAmountTextView: TextView
    lateinit var addButton: Button

    lateinit var db: AppDatabase
    lateinit var toDoDao: ToDoDao
    lateinit var categoryDao: CategoryDao

    lateinit var categories: List<Category>
    var selectedCategoryId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_to_do, container, false)

        initDatabase()

        categories = categoryDao.getAll()

        initUI(view)

        setUI(view)

        return view
    }

    private fun initUI(view: View) {
        titleEditText = view.findViewById(R.id.title_edit_text)
        descriptionEditText = view.findViewById(R.id.description_edit_text)
        daysPlusTextView = view.findViewById(R.id.days_plus_text_view)
        daysAmountTextView = view.findViewById(R.id.days_amount_text_view)
        daysMinusTextView = view.findViewById(R.id.days_minus_text_view)
        hoursPlusTextView = view.findViewById(R.id.hours_plus_text_view)
        hoursAmountTextView = view.findViewById(R.id.hours_amount_text_view)
        hoursMinusTextView = view.findViewById(R.id.hours_minus_text_view)
        addButton = view.findViewById(R.id.add_button)
        autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
    }

    private fun setUI(view: View) {

        setAutoCompleteTextView()

        setDuartionChooseAction()

        addButton.setOnClickListener {

            addToDo()

            Toast.makeText(activity, "ToDo added", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun initDatabase() {
        db = MyApplication.instance.getDatabase()!!
        toDoDao = db.toDoDao()
        categoryDao = db.categoryDao()
    }

    private fun setAutoCompleteTextView() {
        autoCompleteTextView.setText(categories.get(0).title)
        selectedCategoryId = categories.get(0).id!!

        val arrayAdapter = CategoryListAdapter(requireContext(), R.layout.dropdown_item, categories)
        autoCompleteTextView.setAdapter(arrayAdapter)
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            var selectedCategory = parent.getItemAtPosition(position) as Category
            autoCompleteTextView.setText(selectedCategory.title)
            selectedCategoryId = selectedCategory.id!!
        }
    }

    private fun setDuartionChooseAction() {
        daysPlusTextView.setOnClickListener {
            val days: Int = daysAmountTextView.text.toString().toInt()
            daysAmountTextView.text = "${days+1}"
        }
        daysMinusTextView.setOnClickListener {
            val days: Int = daysAmountTextView.text.toString().toInt()
            daysAmountTextView.text = "${days-1}"
        }
        hoursPlusTextView.setOnClickListener {
            val hours: Int = hoursAmountTextView.text.toString().toInt()
            hoursAmountTextView.text = "${hours+1}"
        }
        hoursMinusTextView.setOnClickListener {
            val hours: Int = hoursAmountTextView.text.toString().toInt()
            hoursAmountTextView.text = "${hours-1}"
        }
    }

    private fun addToDo() {
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val status = "Undone"
        val categoryTitle = autoCompleteTextView.text.toString()
        val duration = com.example.todolist.model.Duration(daysAmountTextView.text.toString().toInt(), hoursAmountTextView.text.toString().toInt())
        var categoryId: Int = selectedCategoryId

        val toDo = ToDo(
            title = title,
            description = description,
            status = status,
            category = categoryId,
            duration = duration)

        toDoDao.insert(toDo)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddToDoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddToDoFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}