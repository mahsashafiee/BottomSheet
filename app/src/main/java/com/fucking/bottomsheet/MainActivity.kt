package com.fucking.bottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.fucking.bottomsheet.databinding.ActivityMainBinding
import com.fucking.bottomsheet.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply.setOnClickListener {
            val count = binding.itemCount.text.toString().trim()
            if (count.isEmpty()) return@setOnClickListener
            viewModel.contentCount.value = count.toInt()
            RecipesBottomSheet.newInstance().show(
                supportFragmentManager,
                RecipesBottomSheet::class.java.simpleName
            )
        }
    }
}