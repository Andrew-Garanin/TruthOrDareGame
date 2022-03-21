package com.example.truthordaregame.editcontent

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
import com.example.truthordaregame.addnewcontent.AddNewContentViewModel
import com.example.truthordaregame.addnewcontent.AddNewContentViewModelFactory
import com.example.truthordaregame.database.TruthOrDareGameDatabase
import com.example.truthordaregame.databinding.FragmentEditContentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EditContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditContentFragment : Fragment() {
    private lateinit var viewModel: EditContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentEditContentBinding>(inflater,
            R.layout.fragment_edit_content, container, false)

        val editContentFragmentArgs by navArgs<com.example.truthordaregame.editcontent.EditContentFragmentArgs>()

        val application = requireNotNull(this.activity).application
        val dao = TruthOrDareGameDatabase.getInstance(application).getTruthOrDareGameDatabaseDao()

        val viewModelFactory = EditContentViewModelFactory(dao, application, editContentFragmentArgs.contentID, editContentFragmentArgs.contentString, editContentFragmentArgs.contentType)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EditContentViewModel::class.java)

        binding.editTextEditContent.setText(viewModel.contentString.value)

//        viewModel.contentString.observe(viewLifecycleOwner, Observer { newContentString ->
//
//        })

        viewModel.contentType.observe(viewLifecycleOwner, Observer { newContentType ->
            var actionBar = (activity as AppCompatActivity?)!!.supportActionBar

            if (newContentType == 1) {
                actionBar?.setTitle(R.string.edit_question)
            }
            else
            {
                actionBar?.setTitle(R.string.edit_dare)
            }

            binding.buttonEditContentContentOK.setOnClickListener {
                val text = binding.editTextEditContent.text.toString().trim()
                if (text != ""){
                    if (newContentType == 1) {
                        viewModel.onUpdateQuestion(text)
                        Toast.makeText(application, "Вопрос отредактирован успешно", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else{
                        viewModel.onUpdateDare(text)
                        Toast.makeText(application, "Действие отредактировано успешно", Toast.LENGTH_SHORT)
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


//        binding.buttonEditContentContentOK.setOnClickListener{
//            it.findNavController().navigate(R.id.action_editContentFragment_to_contentListFragment)
//        }
        return binding.root
    }
}