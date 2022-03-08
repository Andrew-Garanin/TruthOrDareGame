package com.example.truthordaregame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.truthordaregame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonStart.setText(binding.buttonStart.text.toString().plus(String(Character.toChars(0x1F3AE))))
        binding.buttonRules.setText(binding.buttonRules.text.toString().plus(String(Character.toChars(0x1F4C3))))
    }
}