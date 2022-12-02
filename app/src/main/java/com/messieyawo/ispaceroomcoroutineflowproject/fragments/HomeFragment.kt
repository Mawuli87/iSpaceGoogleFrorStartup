package com.messieyawo.ispaceroomcoroutineflowproject.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.messieyawo.ispaceroomcoroutineflowproject.MainActivity
import com.messieyawo.ispaceroomcoroutineflowproject.data.adapter.GenresAdapter
import com.messieyawo.ispaceroomcoroutineflowproject.data.models.Data
import com.messieyawo.ispaceroomcoroutineflowproject.databinding.FragmentHomeBinding
import com.messieyawo.ispaceroomcoroutineflowproject.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment(),GenresAdapter.ItemClickListener {
lateinit var binding: FragmentHomeBinding
    lateinit var homeFragmentViewModel: HomeFragmentViewModel

    private lateinit var genresAdapter: GenresAdapter
    private var allGenres = mutableListOf<Data>()

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




        homeFragmentViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }
        })

        homeFragmentViewModel.genres.observe(viewLifecycleOwner, Observer {
            Log.i("GENRES", it.body()?.data.toString())

            if (it.body()?.data!!.isNotEmpty()){


                allGenres.addAll(it.body()!!.data)
                genresAdapter = GenresAdapter(requireActivity(),allGenres,this)

                binding.recyclerView.setHasFixedSize(true)
                binding.recyclerView.layoutManager =  GridLayoutManager(requireActivity(), 2)
                binding.recyclerView.adapter = genresAdapter

                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onItemClick(data: Data) {

    }

}