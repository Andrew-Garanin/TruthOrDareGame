package com.example.truthordaregame.contentlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.truthordaregame.database.TruthOrDareGameDatabase
import com.example.truthordaregame.databinding.FragmentContentListBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.truthordaregame.R


class ContentListFragment : Fragment() {

    private lateinit var viewModel: ContentListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentContentListBinding>(inflater, R.layout.fragment_content_list, container, false)
        val contentListFragmentArgs by navArgs<ContentListFragmentArgs>()
        val application = requireNotNull(this.activity).application
        val dao = TruthOrDareGameDatabase.getInstance(application).getTruthOrDareGameDatabaseDao()

        //----------------------Настройки ViewModel----------------------
        val viewModelFactory = ContentListViewModelFactory(dao, application, contentListFragmentArgs.contentType)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ContentListViewModel::class.java)

        viewModel.contentType.observe(viewLifecycleOwner, { newContentType ->

            val actionBar = (activity as AppCompatActivity?)!!.supportActionBar

            if (newContentType == 1) { // Вопрос
                actionBar?.setTitle(R.string.user_questions)

                val adapter = QuestionsListAdapter(viewModel)
                binding.contentList.adapter = adapter

                viewModel.questions.observe(viewLifecycleOwner, { NewQuestions ->
                    if (NewQuestions != null)
                        adapter.data = NewQuestions
                })
            } else { // Действие
                actionBar?.setTitle(R.string.user_dares)

                val adapter = DaresListAdapter(viewModel)
                binding.contentList.adapter = adapter

                viewModel.dares.observe(viewLifecycleOwner, { NewDares ->
                    if (NewDares != null)
                        adapter.data = NewDares
                })
            }
        })

        binding.buttonAddNewContent.setOnClickListener{
            it.findNavController().navigate(ContentListFragmentDirections.actionContentListFragmentToAddNewContentFragment(
                viewModel.contentType.value!!
            ))
        }
        return binding.root
    }
}
