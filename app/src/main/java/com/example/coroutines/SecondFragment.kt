package com.example.coroutines

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.example.coroutines.databinding.FragmentSecondBinding
import kotlinx.coroutines.*

class SecondFragment : Fragment() {


    lateinit var binding:FragmentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        binding = FragmentSecondBinding.bind(view)
        var startAmount = 0
        var maxAmount = 0
        var start = 0
        setFragmentResultListener(MainActivity.COUNTS){_, bundle ->
            startAmount = bundle.getString(MainActivity.START_AMOUNT, "0").toInt()
            start = startAmount
            binding.amount.text = startAmount.toString()
        }


        maxAmount = requireArguments().getString(MainActivity.COUNTS, "0").toInt()

        binding.plus.setOnClickListener {
            if (startAmount < maxAmount) {
                GlobalScope.launch(Dispatchers.Default) {
                    startAmount ++
                    withContext(Dispatchers.Main) {
                        binding.amount.text = startAmount.toString()
                    }
                }
            } else {
                Toast.makeText(context, "You reached the maximum", Toast.LENGTH_SHORT).show()
                binding.reset.visibility = View.VISIBLE
            }

        }

        binding.minus.setOnClickListener {
            if (startAmount != 0) {
                GlobalScope.launch(Dispatchers.Default) {
                    startAmount--
                    withContext(Dispatchers.Main) {
                        binding.amount.text = startAmount.toString()
                    }
                }
            } else {
                Toast.makeText(context, "You reached the minimum", Toast.LENGTH_SHORT).show()
                binding.reset.visibility = View.VISIBLE
            }

        }
        binding.reset.setOnClickListener {
            binding.amount.text = start.toString()
            startAmount = start
            binding.reset.visibility = View.GONE
        }

        return view
    }
}