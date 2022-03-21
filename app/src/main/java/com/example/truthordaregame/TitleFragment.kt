package com.example.truthordaregame

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.truthordaregame.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)

        binding.buttonStart.setText(binding.buttonStart.text.toString().plus(String(Character.toChars(0x1F3AE))))
        binding.buttonRules.setText(binding.buttonRules.text.toString().plus(String(Character.toChars(0x1F4C3))))

        binding.buttonRules.setOnClickListener{
            it.findNavController().navigate(R.id.action_titleFragment_to_rulesFragment)
        }
        binding.buttonStart.setOnClickListener{
            // Вот тут должны вроде как сформироваться пары впорос-действие
            it.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  NavigationUI.onNavDestinationSelected(item, findNavController()) ||  super.onOptionsItemSelected(item)
    }
}

