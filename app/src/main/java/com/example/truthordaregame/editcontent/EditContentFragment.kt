package com.example.truthordaregame.editcontent

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
import com.example.truthordaregame.ContentType
import com.example.truthordaregame.R
import com.example.truthordaregame.database.TruthOrDareGameDatabase
import com.example.truthordaregame.databinding.FragmentEditContentBinding

class EditContentFragment : Fragment() {
    private lateinit var viewModel: EditContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentEditContentBinding>(inflater, R.layout.fragment_edit_content, container, false)
        val editContentFragmentArgs by navArgs<EditContentFragmentArgs>()
        val application = requireNotNull(this.activity).application
        val dao = TruthOrDareGameDatabase.getInstance(application).getTruthOrDareGameDatabaseDao()

        //---------------------"Адаптация для картинки"---------------------
        val image: ImageView = binding.editContentImage
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
        val viewModelFactory = EditContentViewModelFactory(dao, application, editContentFragmentArgs.contentID, editContentFragmentArgs.contentString, editContentFragmentArgs.contentType)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EditContentViewModel::class.java)

        viewModel.contentType.observe(viewLifecycleOwner, { newContentType ->
            val actionBar = (activity as AppCompatActivity?)!!.supportActionBar

            when(newContentType){
                ContentType.QUESTION->  actionBar?.setTitle(R.string.edit_question)
                ContentType.DARE->  actionBar?.setTitle(R.string.edit_dare)
            }

            binding.buttonEditContentContentOK.setOnClickListener {
                val text = binding.editTextEditContent.text.toString().trim()
                if (text != ""){
                    when(newContentType){
                        ContentType.QUESTION-> {
                            viewModel.onUpdateQuestion(text)
                            Toast.makeText(application, it.context.resources.getString(R.string.question_edited_successfully), Toast.LENGTH_SHORT)
                                .show()
                        }
                        ContentType.DARE-> {
                            viewModel.onUpdateDare(text)
                            Toast.makeText(application,  it.context.resources.getString(R.string.dare_edited_successfully), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    it.findNavController().navigateUp()
                }
                else{
                    when(newContentType){
                        ContentType.QUESTION-> Toast.makeText(application,  it.context.resources.getString(R.string.enter_question), Toast.LENGTH_SHORT).show()
                        ContentType.DARE-> Toast.makeText(application,  it.context.resources.getString(R.string.enter_dare), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        binding.editTextEditContent.setText(viewModel.contentString.value)

        return binding.root
    }
}