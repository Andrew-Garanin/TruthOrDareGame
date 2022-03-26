package com.example.truthordaregame.addnewcontent

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.truthordaregame.R
import com.example.truthordaregame.database.TruthOrDareGameDatabase
import com.example.truthordaregame.databinding.FragmentAddNewContentBinding

class AddNewContentFragment : Fragment() {

    private lateinit var viewModel: AddNewContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentAddNewContentBinding>(inflater, R.layout.fragment_add_new_content, container, false)
        val addNewContentFragmentArgs by navArgs<AddNewContentFragmentArgs>()
        val application = requireNotNull(this.activity).application
        val dao = TruthOrDareGameDatabase.getInstance(application).getTruthOrDareGameDatabaseDao()

        //---------------------"Адаптация для картинки"---------------------
        val image: ImageView = binding.addNewContentImage
        val currentOrientation = resources.configuration.orientation
        val param = image.layoutParams as ViewGroup.MarginLayoutParams
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            // Portrait
            val dp = 50 * context?.resources?.displayMetrics?.density!!
            param.setMargins(dp.toInt(), dp.toInt(), dp.toInt(), dp.toInt())
        } else {
            // Landscape
            val dp = 15 * context?.resources?.displayMetrics?.density!!
            param.setMargins(0, dp.toInt(), 0, dp.toInt())
        }
        image.layoutParams = param

        //----------------------Настройки ViewModel----------------------
        val viewModelFactory = AddNewContentViewModelFactory(dao, application, addNewContentFragmentArgs.contentType)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddNewContentViewModel::class.java)

        viewModel.contentType.observe(viewLifecycleOwner, { newContentType ->
            val actionBar = (activity as AppCompatActivity?)!!.supportActionBar

            if (newContentType == 1) { // Вопрос
                actionBar?.setTitle(R.string.add_question)
                binding.editTextAddNewContent.setHint(R.string.enter_new_question)
            }
            else { // Действие
                actionBar?.setTitle(R.string.add_dare)
                binding.editTextAddNewContent.setHint(R.string.enter_new_dare)
            }

            binding.buttonAddNewContentOK.setOnClickListener {
                val text = binding.editTextAddNewContent.text.toString().trim()
                if (text != ""){
                    if (newContentType == 1) { // Вопрос
                        viewModel.onAddQuestion(text)
                        Toast.makeText(application, it.context.resources.getString(R.string.question_added_successfully), Toast.LENGTH_SHORT)
                            .show()
                    }
                    else { // Действие
                        viewModel.onAddDare(text)
                        Toast.makeText(application, it.context.resources.getString(R.string.dare_added_successfully), Toast.LENGTH_SHORT)
                            .show()
                    }
                    it.findNavController().navigateUp()
                }
                else{

                    if (newContentType == 1) // Вопрос
                        Toast.makeText(application, it.context.resources.getString(R.string.enter_question), Toast.LENGTH_SHORT).show()
                    else // Действие
                        Toast.makeText(application, it.context.resources.getString(R.string.enter_dare), Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.buttonAddNewContentOK.setOnClickListener{
            it.findNavController().navigate(R.id.action_addNewContentFragment_to_contentListFragment)
        }
        return binding.root
    }
}
