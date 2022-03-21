package com.example.truthordaregame.addnewcontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.truthordaregame.R
import com.example.truthordaregame.contentlist.ContentListViewModel
import com.example.truthordaregame.contentlist.ContentListViewModelFactory
import com.example.truthordaregame.contentlist.DaresListAdapter
import com.example.truthordaregame.contentlist.QuestionsListAdapter
import com.example.truthordaregame.database.TruthOrDareGameDatabase
import com.example.truthordaregame.databinding.FragmentAddNewContentBinding
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass.
 * Use the [AddNewContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNewContentFragment : Fragment() {

    private lateinit var viewModel: AddNewContentViewModel
    //private var contentType by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddNewContentBinding>(inflater,
            R.layout.fragment_add_new_content, container, false)

        binding.buttonAddNewContentOK.setOnClickListener{
            it.findNavController().navigate(R.id.action_addNewContentFragment_to_contentListFragment)
        }

        val addNewContentFragmentArgs by navArgs<com.example.truthordaregame.addnewcontent.AddNewContentFragmentArgs>()
        //var contentType = addNewContentFragmentArgs.contentType


        val application = requireNotNull(this.activity).application
        val dao = TruthOrDareGameDatabase.getInstance(application).getTruthOrDareGameDatabaseDao()

        val viewModelFactory = AddNewContentViewModelFactory(dao, application, addNewContentFragmentArgs.contentType)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddNewContentViewModel::class.java)

        viewModel.contentType.observe(viewLifecycleOwner, Observer { newContentType ->
            //contentType= newcontentType
            var actionBar = (activity as AppCompatActivity?)!!.supportActionBar

            if (newContentType == 1) {
                actionBar?.setTitle(R.string.add_question)
                binding.editTextAddNewContent.hint = "Введите новый вопрос"
            }
            else
            {
                actionBar?.setTitle(R.string.add_dare)
                binding.editTextAddNewContent.hint = "Введите новое действие"
            }

            binding.buttonAddNewContentOK.setOnClickListener {
                val text = binding.editTextAddNewContent.text.toString().trim()
                if (text != ""){
                    if (newContentType == 1) {
                        viewModel.onAddQuestion(text)
                        Toast.makeText(application, "Вопрос добавлен успешно", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else{
                        viewModel.onAddDare(text)
                        Toast.makeText(application, "Действие добавлено успешно", Toast.LENGTH_SHORT)
                            .show()
                    }
                    it.findNavController().navigateUp()
                }
                else{
                    if (newContentType == 1)
                        Toast.makeText(application, "Введите вопрос", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(application, "Введите действие", Toast.LENGTH_SHORT).show()
                }

            }
        })



        return binding.root
    }
}