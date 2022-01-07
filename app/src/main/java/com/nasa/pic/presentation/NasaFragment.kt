package com.nasa.pic.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nasa.pic.NasaApplication
import com.nasa.pic.R
import com.nasa.pic.databinding.FragmentNasaBinding
import com.nasa.pic.presentation.viewmodel.NasaViewModel
import javax.inject.Inject

class NasaFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = NasaFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        NasaApplication.instance.getNasaAppComponent().inject(this)
        val nasaViewModel: NasaViewModel by viewModels { viewModelFactory }
        val binding = DataBindingUtil.inflate<FragmentNasaBinding>(
            inflater, R.layout.fragment_nasa, container, false
        )

        return binding.root
    }


}
