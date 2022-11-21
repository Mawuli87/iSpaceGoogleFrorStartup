package com.messieyawo.ispaceroomcoroutineflowproject.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.messieyawo.ispaceroomcoroutineflowproject.MainActivity
import com.messieyawo.ispaceroomcoroutineflowproject.R
import com.messieyawo.ispaceroomcoroutineflowproject.databinding.FragmentHomeBinding
import com.messieyawo.ispaceroomcoroutineflowproject.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment() {
lateinit var binding: FragmentHomeBinding
    lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeFragmentViewModel = (activity as MainActivity).homeFragmentViewModel

        homeFragmentViewModel.getAllCategoryList()

        homeFragmentViewModel.genres.observe(viewLifecycleOwner, Observer {
            Log.i("GENRES", it.body()?.data.toString())
        })
    }

}