package com.example.coroutines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.coroutines.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding:FragmentMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.bind(view)
        binding.submit.setOnClickListener {
            val maxAmount = binding.maxAmount.text.toString()
            val startAmount = binding.startAmount.text.toString()
            try {
                if (maxAmount != "" && startAmount != "" && startAmount.toInt() < maxAmount.toInt()) {
                    setFragmentResult(
                            MainActivity.COUNTS, bundleOf(
                            MainActivity.START_AMOUNT to startAmount
                        )
                    )
                    val bundle = Bundle()
                    bundle.putString(MainActivity.MAX_AMOUNT, maxAmount)
//                    bundle.putInt(MainActivity.START_AMOUNT, startAmount.toInt())
                    it.findNavController().navigate(R.id.action_mainFragment_to_secondFragment, bundle)
                }
            }catch(e:Exception){
                Toast.makeText(context, "Incorrect input", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}
